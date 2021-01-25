package com.xtel.core.sys.model.config_schedule;

import com.xtel.core.dto.response.customer.ConfigScheduleResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListConfigScheduleCmd extends CallableStatementCmd {
    private String phone_number;
    private List<ConfigScheduleResponse> data;
    public DbGetListConfigScheduleCmd(String transid, String channel, String phone_number) {
        super(transid, channel);
        this.phone_number = phone_number;
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(5, ConfigScheduleResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CONFIG_SCHEDULE.get_list_data", 4);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(phone_number);
        register(OracleTypes.CURSOR);
    }

    public List<ConfigScheduleResponse> getData() {
        return data;
    }
}
