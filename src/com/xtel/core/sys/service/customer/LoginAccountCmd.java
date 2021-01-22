package com.xtel.core.sys.service.customer;

import com.google.gson.Gson;
import com.tbv.utils.textbase.StringUtils;
import com.xtel.core.dto.request.customer.LoginAccountRequest;
import com.xtel.core.dto.response.customer.DataAccount;
import com.xtel.core.secure.JWTUtil;
import com.xtel.core.sys.model.customer.DbLoginAccountCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class LoginAccountCmd extends AbsBodyRequestCmd {
    private LoginAccountRequest request;
    public LoginAccountCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(LoginAccountRequest.class);
        String full_name = request.getFull_name();
        String password = StringUtils.encodeMD5(request.getPassword());

        DbLoginAccountCmd dbCmd = new DbLoginAccountCmd(transid, channel, full_name, password);
        executeDbCmd(dbCmd);

        if(dbCmd.getCode() != 0){
            setResponse(dbCmd.getCode(), dbCmd.getMessage());
            return;
        }

        int expire_time = 1;
        if(request.getRemember() == 1){
            expire_time = 15;
        }
        DataAccount dataAccount = dbCmd.getData();
        String token = JWTUtil.encode(dataAccount.getCustomerResponse().getEMAIL(), new Gson().toJson(dataAccount), expire_time);
        dataAccount.setToken(token);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dataAccount);
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
