package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.sys.model.config_schedule.DbGetDetailConfigScheduleCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailConfigScheduleCmd extends AbsQueryParamRequestCmd {
    private Integer config_schedule_id;
    private String phone_number;

    public GetDetailConfigScheduleCmd(HttpServletRequest httpServletRequest, String phone_number, Integer config_schedule_id) {
        super(httpServletRequest);
        this.config_schedule_id = config_schedule_id;
        this.phone_number = phone_number;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailConfigScheduleCmd dbCmd = new DbGetDetailConfigScheduleCmd(transid, channel, phone_number, config_schedule_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
