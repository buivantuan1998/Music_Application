package com.xtel.core.sys.model.song;

import com.xtel.core.dto.response.song.SongResponse;
import com.xtel.core.sys.entities.Song;
import oracle.jdbc.OracleTypes;
import com.xtel.core.sys.model.CallableStatementCmd;
import java.sql.Types;
import java.util.List;

public class DbGetListSongCmd extends CallableStatementCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;
    private Integer play_list_id;
    private Integer customer_id;
    private Integer album_id;
    private List<SongResponse> data;

    public DbGetListSongCmd(String transid, String channel, Integer page_index, Integer page_size, String search_name,
                            String order_by, Integer play_list_id, Integer customer_id, Integer album_id) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
        this.play_list_id = play_list_id;
        this.customer_id = customer_id;
        this.album_id = album_id;
    }

    @Override
    protected void getResult() throws Exception {
        data = getList(14, SongResponse.class);
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("PKG_SONG.get_list_song", 14);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        register(Types.INTEGER);
        register(Types.VARCHAR);
        setInt(page_index);
        setInt(page_size);
        setString(search_name);
        setString(order_by);
        setInt(play_list_id);
        setInt(customer_id);
        setInt(album_id);
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
