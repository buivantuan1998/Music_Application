package com.xtel.core.sys.model.album;

import com.xtel.core.sys.model.CallableStatementCmd;

import java.sql.Types;

public class DbDeleteAlbumCmd extends CallableStatementCmd {
    private String phone_number;
    private Integer album_id;

    public DbDeleteAlbumCmd(String transid, String channel, String phone_number, Integer album_id) {
        super(transid, channel);
        this.phone_number = phone_number;
        this.album_id = album_id;
    }

    @Override
    protected void getResult() throws Exception {

    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_ALBUM.delete_data", 4);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setString(phone_number);
        setInt(album_id);
    }
}
