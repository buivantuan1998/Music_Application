package com.xtel.core.sys.model.song;

import com.xtel.core.dto.request.song.DeleteSongInPlayListRequest;
import com.xtel.core.dto.request.song.DeleteSongRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbDeleteSongInPlayListCmd extends MultiCallableStatementCmd {
    private DeleteSongInPlayListRequest request;

    public DbDeleteSongInPlayListCmd(String transid, String channel, DeleteSongInPlayListRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_SONG.delete_song_out_play_list", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getCode_song());
                setInt(cst, i++, request.getPlay_list_id());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
