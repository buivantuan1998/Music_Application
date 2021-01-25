package com.xtel.core.sys.model.config_schedule;

import com.xtel.core.dto.request.config_schedule.InsertConfigScheduleRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbInsertConfigScheduleCmd extends MultiCallableStatementCmd {
    private InsertConfigScheduleRequest request;
    private Integer config_schedule_id;

    public DbInsertConfigScheduleCmd(String transid, String channel, InsertConfigScheduleRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CONFIG_SCHEDULE.insert_config_schedule", 10, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getName());
                setInt(cst, i++, request.getStart_hour());
                setInt(cst, i++, request.getEnd_hour());
                setInt(cst, i++, request.getStart_minute());
                setInt(cst, i++, request.getEnd_minute());
                setString(cst, i++, request.getPhone_number());
                setInt(cst, i++, request.getStatus());
                register(cst, i++, Types.INTEGER);
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
                config_schedule_id = cst.getInt(10);
            }
        });
    }

    public Integer getConfig_schedule_id() {
        return config_schedule_id;
    }
}
