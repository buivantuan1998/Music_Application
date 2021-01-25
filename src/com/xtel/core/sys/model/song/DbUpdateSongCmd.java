package com.xtel.core.sys.model.song;

import com.xtel.core.dto.request.song.UpdateSongRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbUpdateSongCmd extends MultiCallableStatementCmd {
    private UpdateSongRequest request;

    public DbUpdateSongCmd(String transid, String channel, UpdateSongRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_SONG.update_data", 12, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getSong_id());
                setString(cst, i++, request.getSong_name());
                setString(cst, i++, request.getUrl());
                setTimestamp(cst, i++, request.getRelase_time());
                setInt(cst, i++, request.getTime());
                setString(cst, i++, request.getSinger_name());
                setString(cst, i++, request.getMusician_name());
                setInt(cst, i++, request.getCategory_id());
                setString(cst, i++, request.getCreate_by());
                setString(cst, i++, request.getCode_song());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
