package com.xtel.core.sys.service;

import com.xtel.core.common.ErrorTypes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbsQueryParamRequestCmd extends AbsApiBaseCmd {

    public AbsQueryParamRequestCmd(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    public void execute() {

        try {
            logger.debug(String.format("BEGIN_CMD transid: %s, channel: %s, request: %s", transid, channel, logRequest()));

            if (!isValidToken()) {
                if (objResponse == null) {
                    setResponse(ErrorTypes.TOKEN_INVALID);
                }
                createResponse();
                return;
            }

            if (!isValidData()) {
                if (objResponse == null) {
                    setResponse(ErrorTypes.REQUEST_INVALID);
                }
                createResponse();
                return;
            }

            executeCmd();
        } catch (Exception e) {
            if (objResponse == null) {
                setResponse(ErrorTypes.UNKNOWN);
            }
            logger.warn(String.format("transid: %s, channel: %s, request: %s, exception: %s",
                    transid, channel, logRequest(), e.getMessage()), e);
        }

        createResponse();
    }

}
