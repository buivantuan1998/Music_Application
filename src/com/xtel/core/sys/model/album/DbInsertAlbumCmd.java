package com.xtel.core.sys.model.album;

import com.xtel.core.sys.model.CallableStatementCmd;
import java.sql.Types;

public class DbInsertAlbumCmd extends CallableStatementCmd {
    private Integer customer_id;
    private String create_by;
    private String album_name;
    private Integer album_id;

    public DbInsertAlbumCmd(String transid, String channel, Integer customer_id, String create_by, String album_name) {
        super(transid, channel);
        this.customer_id = customer_id;
        this.create_by = create_by;
        this.album_name = album_name;
    }

    @Override
    protected void getResult() throws Exception {
        album_id = cst.getInt(6);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_ALBUM.insert_data", 6);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(customer_id);
        setString(create_by);
        setString(album_name);
        register(Types.INTEGER);
    }

    public Integer getAlbum_id() {
        return album_id;
    }
}
