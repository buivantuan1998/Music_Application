package com.xtel.core.sys.model.song;

import com.xtel.core.sys.entities.Song;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;

public class DbGetDetailSongCmd extends CallableStatementCmd {
    private String code_song;
    private Song data;

    public DbGetDetailSongCmd(String transid, String channel, String code_song) {
        super(transid, channel);
        this.code_song = code_song;
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
        setString(code_song);
        register(OracleTypes.CURSOR);
    }

    public Song getData() {
        return data;
    }
}
