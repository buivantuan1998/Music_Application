package com.xtel.core.sys.model.config_schedule;

import com.xtel.core.dto.request.config_schedule.UpdateConfigScheduleRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbUpdateConfigScheduleCmd extends MultiCallableStatementCmd {
    private UpdateConfigScheduleRequest request;

    public DbUpdateConfigScheduleCmd(String transid, String channel, UpdateConfigScheduleRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CONFIG_SCHEDULE.update_config_schedule", 10, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getConfig_schedule_id());
                setString(cst, i++, request.getName());
                setInt(cst, i++, request.getStart_hour());
                setInt(cst, i++, request.getEnd_hour());
                setInt(cst, i++, request.getStart_minute());
                setInt(cst, i++, request.getEnd_minute());
                setInt(cst, i++, request.getCustomer_id());
                setInt(cst, i++, request.getStatus());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
