package com.xtel.core.sys.model.song;

import com.xtel.core.dto.request.song.DeleteSongInAlbumRequest;
import com.xtel.core.sys.model.MultiCallableStatementCmd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DbDeleteSongInAlbumCmd extends MultiCallableStatementCmd {
    private DeleteSongInAlbumRequest request;
    public DbDeleteSongInAlbumCmd(String transid, String channel, DeleteSongInAlbumRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        executeProcedure("PKG_SONG.delete_song_out_album", 4, new Procedure() {
            @Override
            public void setProcedure(CallableStatement cst) throws SQLException {
                int i = 1;
                register(cst, i++, Types.INTEGER);
                register(cst, i++, Types.VARCHAR);
                setString(cst, i++, request.getCode_song());
                setInt(cst, i++, request.getAlbum_id());
            }

            @Override
            public void getOutput(CallableStatement cst) throws Exception {
                code = cst.getInt(1);
                message = cst.getString(2);
            }
        });
    }
}
