package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.dto.request.config_schedule.DeleteConfigScheduleRequest;
import com.xtel.core.sys.model.config_schedule.DbDeleteConfigScheduleCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeleteConfigScheduleCmd extends AbsBodyRequestCmd {
    private DeleteConfigScheduleRequest request;
    public DeleteConfigScheduleCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(DeleteConfigScheduleRequest.class);

        DbDeleteConfigScheduleCmd dbCmd = new DbDeleteConfigScheduleCmd(transid, channel, request, getIdFromToken());
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
