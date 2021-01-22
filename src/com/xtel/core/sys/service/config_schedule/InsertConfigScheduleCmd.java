package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.dto.request.config_schedule.InsertConfigScheduleRequest;
import com.xtel.core.sys.model.config_schedule.DbInsertConfigScheduleCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class InsertConfigScheduleCmd extends AbsBodyRequestCmd {
    private InsertConfigScheduleRequest request;
    public InsertConfigScheduleCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertConfigScheduleRequest.class);

        DbInsertConfigScheduleCmd dbCmd = new DbInsertConfigScheduleCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getConfig_schedule_id());
    }
}
