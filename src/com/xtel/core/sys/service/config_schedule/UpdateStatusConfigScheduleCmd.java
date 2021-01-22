package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.dto.request.config_schedule.UpdateStatusConfigScheduleRequest;
import com.xtel.core.sys.model.config_schedule.DbUpdateStatusConfigScheduleCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UpdateStatusConfigScheduleCmd extends AbsBodyRequestCmd {
    private UpdateStatusConfigScheduleRequest request;
    public UpdateStatusConfigScheduleCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(UpdateStatusConfigScheduleRequest.class);

        DbUpdateStatusConfigScheduleCmd dbCmd = new DbUpdateStatusConfigScheduleCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
