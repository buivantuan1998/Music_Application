package com.xtel.core.sys.model.song;

import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;

public class DbGetSongDefaultCmd extends CallableStatementCmd {
    private String phone_number;
    private SongResponse data;
    public DbGetSongDefaultCmd(String transid, String channel, String phone_number) {
        super(transid, channel);
        this.phone_number = phone_number;
    }

    @Override
    protected void getResult() throws Exception {
        data = getSingle(4, SongResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.get_song_defauft", 4);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(phone_number);
        register(OracleTypes.CURSOR);
    }

    public SongResponse getData() {
        return data;
    }
}
