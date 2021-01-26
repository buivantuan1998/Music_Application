package com.xtel.core.sys.model.config_schedule;

import com.xtel.core.dto.request.config_schedule.ChangeSongDefaultRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbChangeSongDefaultCmd extends MultiCallableStatementCmd {
    private ChangeSongDefaultRequest request;

    public DbChangeSongDefaultCmd(String transid, String channel, ChangeSongDefaultRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_CONFIG_SCHEDULE.change_song_in_schedule", 5, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getCode_song());
                setString(cst, i++, request.getPhone_number());
                setInt(cst, i++, request.getConfig_schedule_id());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
