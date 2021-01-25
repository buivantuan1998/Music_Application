package com.xtel.core.sys.model.song;

import com.xtel.core.sys.model.CallableStatementCmd;
import java.sql.Types;

public class DbSetDefaultSongCmd extends CallableStatementCmd {
    private String code_song;
    private String phone_number;

    public DbSetDefaultSongCmd(String transid, String channel, String code_song, String phone_number) {
        super(transid, channel);
        this.code_song = code_song;
        this.phone_number = phone_number;
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
        setString(code_song);
        setString(phone_number);
    }
}
