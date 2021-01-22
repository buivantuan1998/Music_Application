package com.xtel.core.sys.model.song;

import com.xtel.core.sys.entities.Song;
import com.xtel.core.sys.model.CallableStatementCmd;
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
        data = getSingle(4, Song.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.get_detail_song", 4);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(song_id);
        register(OracleTypes.CURSOR);
    }

    public Song getData() {
        return data;
    }
}
