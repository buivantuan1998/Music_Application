package com.xtel.core.sys.model.album;

import com.xtel.core.sys.model.CallableStatementCmd;

import java.sql.Types;

public class DbDeleteAlbumCmd extends CallableStatementCmd {
    private Integer customer_id;
    private Integer album_id;

    public DbDeleteAlbumCmd(String transid, String channel, Integer customer_id, Integer album_id) {
        super(transid, channel);
        this.customer_id = customer_id;
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
        setInt(customer_id);
        setInt(album_id);
    }
}
