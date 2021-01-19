package com.xtel.core.sys.model.song;

import com.xtel.core.sys.model.CallableStatementCmd;;import java.sql.Types;

public class DbDeleteSongCmd extends CallableStatementCmd {
    private Integer song_id;

    public DbDeleteSongCmd(String transid, String channel, Integer song_id) {
        super(transid, channel);
        this.song_id = song_id;
    }

    @Override
    protected void getResult() throws Exception {

    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.delete_song", 3);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(song_id);
    }
}
