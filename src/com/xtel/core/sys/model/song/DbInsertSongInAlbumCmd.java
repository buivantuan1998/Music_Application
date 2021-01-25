package com.xtel.core.sys.model.song;

import com.xtel.core.dto.request.song.InsertSongInAlbumRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbInsertSongInAlbumCmd extends MultiCallableStatementCmd {
    private InsertSongInAlbumRequest request;

    public DbInsertSongInAlbumCmd(String transid, String channel, InsertSongInAlbumRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_SONG.insert_song_on_album", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setInt(cst, i++, request.getAlbum_id());
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
