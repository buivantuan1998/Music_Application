package com.xtel.core.sys.model.config_schedule;

import com.xtel.core.dto.request.config_schedule.DeleteConfigScheduleRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbDeleteConfigScheduleCmd extends MultiCallableStatementCmd {
    private DeleteConfigScheduleRequest request;

    public DbDeleteConfigScheduleCmd(String transid, String channel, DeleteConfigScheduleRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CONFIG_SCHEDULE.delete_data", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getConfig_schedule_id());
                setString(cst, i++, request.getPhone_number());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
