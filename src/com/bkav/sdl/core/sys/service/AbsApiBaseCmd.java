package com.bkav.sdl.core.sys.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bkav.sdl.core.MainApplication;
import com.bkav.sdl.core.common.Error;
import com.bkav.sdl.core.common.*;
import com.bkav.sdl.core.config.service.CoreConfig;
import com.bkav.sdl.core.secure.JWTUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tbv.utils.db.DBLogic;
import com.tbv.utils.db.cmd.DbCommand;
import com.tbv.utils.textbase.StringUtil;
import com.tbv.utils.textbase.TimeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Date;
import java.util.UUID;

public abstract class AbsApiBaseCmd {

    protected final Log logger = LogFactory.getLog(this.getClass());

    protected static DBLogic dbCtrl = MainApplication.dbModule.getDbCtrl();

    protected static Gson gson = new GsonBuilder()
            .setDateFormat(CoreConfig.DATE_TIME_FORMAT)
            .serializeNulls().create();

    protected HttpServletRequest httpServletRequest;

    protected String transid = UUID.randomUUID().toString();
    protected String channel = "unknown";
    protected String token;

    protected String jsonRequest;
    protected Class<?> classRequest;
    protected Object objRequest;
    protected UserLoginData userLoginData;

    protected String strResponse = null;
    protected BaseResponse objResponse;
    protected Response response;

    protected long begin_time;
    protected long end_time;
    protected boolean hideLog = false;

    public AbsApiBaseCmd(HttpServletRequest httpServletRequest) {
        super();
        this.begin_time = System.currentTimeMillis();
        this.httpServletRequest = httpServletRequest;
        this.token = getToken();
        this.channel = channel;
        this.transid = transid;
    }

    public AbsApiBaseCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> clazz) {
        super();
        this.begin_time = System.currentTimeMillis();
        this.httpServletRequest = httpServletRequest;
        this.token = getToken();
        this.jsonRequest = jsonRequest;
        this.classRequest = clazz;
    }


    protected String getToken() {
        if (httpServletRequest == null) {
            return null;
        }

        String token = httpServletRequest.getHeader(Constant.TOKEN_KEY);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return token;
    }

    protected boolean isValidToken() {
        JWTUtil jwtUtil = new JWTUtil();
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = jwtUtil.decodeJWT(token);
        } catch (Exception ex) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            logger.info(String.format("transid: %s, DecodeJWT exception : %s", transid, ex), ex);
        }

        if (decodedJWT == null) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            return false;
        }
        Date expireDate = null;
        try {
            expireDate = decodedJWT.getExpiresAt();
        } catch (Exception ex) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            logger.info(String.format("transid: %s, DecodeJWT exception : %s", transid, ex), ex);
        }

        if (expireDate == null || new Date().after(expireDate)) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_EXPIRED);
            return false;
        }
        String strJsonData = null;
        try {
            strJsonData = jwtUtil.decodeJWT2(token);
        } catch (Exception ex) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            logger.info(String.format("transid: %s, DecodeJWT exception : %s", transid, ex), ex);
        }
        if (StringUtil.isNullOrEmpty(strJsonData)) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            return false;
        }

        try {
            userLoginData = gson.fromJson(strJsonData, UserLoginData.class);
        } catch (Exception ex) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            logger.info(String.format("DecodeJWT exception : %s", ex));
        }

        logger.debug(
                String.format("transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, accLogin: %s",
                        transid, channel, logRequest(), jsonRequest, classRequest, userLoginData));

        if (userLoginData == null) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.TOKEN_INVALID);
            return false;
        }

        logger.debug(String.format(
                "transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, token pass validation",
                transid, channel, logRequest(), jsonRequest, classRequest));

        return true;
    }

    protected boolean isValidData() throws Exception {
        return true;
    }

    protected abstract void executeCmd() throws Exception;

    protected void executeDbCmd(DbCommand dbCmd) {

        try {
            dbCtrl.executeCommand(dbCmd);
        } catch (Exception e) {
            logger.warn(String.format("transid: %s, Exception when execute Db Command", transid), e);
        }
    }

    protected void createResponse() {

        ResponseBuilder builder = null;

        if (objResponse == null) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.UNKNOWN);
        }

        strResponse = gson.toJson(objResponse);

        try {
            builder = Response.ok();
            builder.entity(strResponse);
        } catch (Exception e) {
            builder = Response.status(Response.Status.SERVICE_UNAVAILABLE.getStatusCode());
            builder.entity(e.toString());
        }

        logEndCmd();
        response = builder.build();
    }

    public Response getResponse() {
        return response;
    }

    public String logResponse() {
        if (hideLog) {
            return "Log is disable !";
        }
        return StringUtil.cutString(strResponse, CoreConfig.RESPONSE_LOG_MAX_LENGTH);
    }

    public String logRequest() {
        if (hideLog) {
            return "Log is disable";
        }

        if (httpServletRequest == null)
            return null;

        return String.format("[from_addr: %s, method: %s, path: %s, param: %s, token: %s]",
                httpServletRequest.getRemoteAddr(), httpServletRequest.getMethod(), httpServletRequest.getPathInfo(),
                httpServletRequest.getQueryString(), StringUtil.cutString(token, CoreConfig.TOKEN_LOG_MAX_LENGTH));
    }

    public String logTimeExecute() {
        return TimeUtils.getDurations(System.currentTimeMillis(), begin_time);
    }

    public UserLoginData getUserLoginData() {
        return userLoginData;
    }

    public Integer getIdFromToken() {
        if (userLoginData == null) {
            return 0;
        }
        return userLoginData.getUser_id();
    }

    public String getEmailFromToken() {
        if (userLoginData == null) {
            return null;
        }

        return userLoginData.getEmail();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [transid=" + transid + ", channel=" + channel + "]";
    }

    protected void setResponse(int dbCode, String dbMsg, Object objData) {
        objResponse = new BaseResponse(transid, new Date(), dbCode, dbMsg, objData);
    }

    protected void setResponse(Error error) {
        objResponse = new BaseResponse(transid, new Date(), error);
    }

    protected void setResponse(BaseResponse baseResponse){
        objResponse = baseResponse;
    }

    protected void setResponse(int code, String msg) {
        objResponse = new BaseResponse(transid, new Date(), code, msg);
    }

    protected void setResponse(Error error, Object data) {
        objResponse = new BaseResponse(transid, new Date(), error, data);
    }

    protected <T> T getObject(Class<T> t) {
        T obj = null;
        try {
            obj = (T) objRequest;
        } catch (Exception ex) {
            objResponse = new BaseResponse(transid, new Date(), ErrorTypes.REQUEST_INVALID);
        }
        return obj;
    }

    protected void logEndCmd() {
        logger.info(
                String.format("END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, cmd: %s, time: %s, response: %s",
                        transid, channel, logRequest(), jsonRequest, classRequest, (hideLog ? "Log is disable !" : this.toString()),
                        logTimeExecute(),
                        logResponse()));
    }
}
