package com.bkav.sdl.core.sys.model.song;

import com.bkav.sdl.core.sys.entities.Song;
import com.bkav.sdl.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;

public class DbGetDetailSongCmd extends CallableStatementCmd {
    private Integer song_id;
    private Song data;

    public DbGetDetailSongCmd(String transid, String channel, Integer song_id) {
        super(transid, channel);
        this.song_id = song_id;
    }

    @Override
    protected void getResult() throws Exception {
        data = getSingle(Song.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.get_detail_song", 4);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        register(OracleTypes.CURSOR);
        setInt(song_id);
    }

    public Song getData() {
        return data;
    }
}
