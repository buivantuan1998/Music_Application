package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.dto.request.config_schedule.ChangeSongDefaultRequest;
import com.xtel.core.sys.model.config_schedule.DbChangeSongDefaultCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class ChangeSongDefaultCmd extends AbsBodyRequestCmd {
    private ChangeSongDefaultRequest request;

    public ChangeSongDefaultCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(ChangeSongDefaultRequest.class);

        DbChangeSongDefaultCmd dbCmd = new DbChangeSongDefaultCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
