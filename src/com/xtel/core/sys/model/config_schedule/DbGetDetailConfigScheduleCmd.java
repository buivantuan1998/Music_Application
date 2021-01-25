package com.xtel.core.sys.model.config_schedule;

import com.xtel.core.dto.request.song.GetSongResponse;
import com.xtel.core.dto.response.config_schedule.GetDetailConfigScheduleResponse;
import com.xtel.core.dto.response.customer.ConfigScheduleResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;

public class DbGetDetailConfigScheduleCmd extends CallableStatementCmd {
    private String phone_number;
    private Integer config_schedule_id;

    private GetDetailConfigScheduleResponse data;
    private ConfigScheduleResponse configScheduleResponse;
    private GetSongResponse songResponse;
    public DbGetDetailConfigScheduleCmd(String transid, String channel, String phone_number, Integer config_schedule_id) {
        super(transid, channel);
        this.phone_number = phone_number;
        this.config_schedule_id = config_schedule_id;
    }

    @Override
    protected void getResult() throws Exception {
        data = new GetDetailConfigScheduleResponse();

        configScheduleResponse = getSingle(5, ConfigScheduleResponse.class);
        data.setConfigScheduleResponse(configScheduleResponse);

        songResponse = getSingle(6, GetSongResponse.class);
        data.setSongResponse(songResponse);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_CONFIG_SCHEDULE.get_detail_data", 6);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(config_schedule_id);
        setString(phone_number);
        register(OracleTypes.CURSOR);
        register(OracleTypes.CURSOR);
    }

    public GetDetailConfigScheduleResponse getData() {
        return data;
    }
}
