package com.xtel.core.sys.model.album;

import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListAlbumCmd extends CallableStatementCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;

    private List<AlbumResponse> data;

    public DbGetListAlbumCmd(String transid, String channel, Integer page_index, Integer page_size, String search_name, String order_by) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(11, AlbumResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_ALBUM.get_list_data", 11);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(page_index);
        setInt(page_size);
        setString(search_name);
        setString(order_by);
        setInt(Types.INTEGER);
        setInt(Types.INTEGER);
        setInt(Types.INTEGER);
        setInt(Types.INTEGER);
        register(OracleTypes.CURSOR);
    }

    public List<AlbumResponse> getData() {
        return data;
    }
}
