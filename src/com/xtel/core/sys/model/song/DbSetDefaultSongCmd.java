package com.xtel.core.sys.model.song;

import com.xtel.core.sys.model.CallableStatementCmd;
import java.sql.Types;

public class DbSetDefaultSongCmd extends CallableStatementCmd {
    private Integer customer_id;
    private Integer song_id;

    public DbSetDefaultSongCmd(String transid, String channel, Integer customer_id, Integer song_id) {
        super(transid, channel);
        this.customer_id = customer_id;
        this.song_id = song_id;
    }

    @Override
    protected void getResult() throws Exception {

    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.set_song_is_default", 4);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(customer_id);
        setInt(song_id);
    }
}
