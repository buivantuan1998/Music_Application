package com.xtel.core.sys.model.song;

import com.xtel.core.dto.request.song.InsertSongInPlayListRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbInsertSongInPlayListCmd extends MultiCallableStatementCmd {
    private InsertSongInPlayListRequest request;
    public DbInsertSongInPlayListCmd(String transid, String channel, InsertSongInPlayListRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_SONG.insert_song_on_play_list", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getPlay_list_id());
                setInt(cst, i++, request.getSong_id());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
