package com.xtel.core.sys.service.config_schedule;

import com.xtel.core.sys.model.config_schedule.DbGetListConfigScheduleCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetListConfigScheduleCmd extends AbsQueryParamRequestCmd {
    private Integer customer_id;
    private String phone_number;

    public GetListConfigScheduleCmd(HttpServletRequest httpServletRequest, String phone_number) {
        super(httpServletRequest);
        this.customer_id = customer_id;
        this.phone_number = phone_number;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetListConfigScheduleCmd dbCmd = new DbGetListConfigScheduleCmd(transid, channel, phone_number);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
