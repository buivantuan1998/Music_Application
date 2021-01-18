package com.bkav.sdl.core.sys.model.song;

import com.bkav.sdl.core.dto.response.song.SongResponse;
import com.bkav.sdl.core.sys.model.CallableStatementCmd;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

public class DbGetListSongCmd extends CallableStatementCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;
    private List<SongResponse> data;

    public DbGetListSongCmd(String transid, String channel, Integer page_index, Integer page_size, String search_name, String order_by) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(SongResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.get_list_song", 11);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(page_index);
        setInt(page_size);
        setString(search_name);
        setString(order_by);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(Types.INTEGER);
        register(OracleTypes.CURSOR);
    }

    public List<SongResponse> getData() {
        return data;
    }
}
