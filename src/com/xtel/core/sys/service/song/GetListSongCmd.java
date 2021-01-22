package com.xtel.core.sys.service.song;

import com.xtel.core.sys.model.song.DbGetListSongCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetListSongCmd extends AbsQueryParamRequestCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;
    private Integer play_list_id;
    private Integer customer_id;
    private Integer album_id;

    public GetListSongCmd(HttpServletRequest httpServletRequest, Integer page_index, Integer page_size, String search_name,
                          String order_by, Integer play_list_id, Integer customer_id, Integer album_id) {
        super(httpServletRequest);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
        this.play_list_id = play_list_id;
        this.customer_id = customer_id;
        this.album_id = album_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetListSongCmd dbCmd = new DbGetListSongCmd(transid, channel, page_index, page_size, search_name,
                                                        order_by, play_list_id, customer_id, album_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
