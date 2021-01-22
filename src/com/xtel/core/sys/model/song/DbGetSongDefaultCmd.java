package com.xtel.core.sys.model.song;

import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;

public class DbGetSongDefaultCmd extends CallableStatementCmd {
    private Integer customer_id;
    private SongResponse data;
    public DbGetSongDefaultCmd(String transid, String channel, Integer customer_id) {
        super(transid, channel);
        this.customer_id = customer_id;
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
        setInt(customer_id);
        register(OracleTypes.CURSOR);
    }

    public SongResponse getData() {
        return data;
    }
}
