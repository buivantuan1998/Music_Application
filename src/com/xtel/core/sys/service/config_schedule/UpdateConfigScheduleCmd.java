package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.dto.request.config_schedule.UpdateConfigScheduleRequest;
import com.xtel.core.sys.model.config_schedule.DbUpdateConfigScheduleCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UpdateConfigScheduleCmd extends AbsBodyRequestCmd {
    private UpdateConfigScheduleRequest request;
    public UpdateConfigScheduleCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(UpdateConfigScheduleRequest.class);

        DbUpdateConfigScheduleCmd dbCmd = new DbUpdateConfigScheduleCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
