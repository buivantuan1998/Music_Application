package com.xtel.core.sys.model.song;
import com.xtel.core.dto.request.song.InsertSongRequest;
import com.xtel.core.sys.model.CallableStatementCmd;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbInsertSongCmd extends MultiCallableStatementCmd {
    private InsertSongRequest request;
    private Integer song_id;

    public DbInsertSongCmd(String transid, String channel, InsertSongRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_SONG.insert_data", 12, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getSong_name());
                setString(cst, i++, request.getUrl());
                setTimestamp(cst, i++, request.getRelase_time());
                setInt(cst, i++, request.getTime());
                setString(cst, i++, request.getSinger_name());
                setString(cst, i++, request.getMusician_name());
                setInt(cst, i++, request.getCategory_id());
                setString(cst, i++, request.getCreate_by());
                register(cst, i++, Types.INTEGER);
                setString(cst, i++, request.getCode_song());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
                song_id = cst.getInt(11);
            }
        });
    }

    public Integer getSong_id() {
        return song_id;
    }
}
