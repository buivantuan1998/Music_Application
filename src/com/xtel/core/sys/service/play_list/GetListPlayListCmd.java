package com.xtel.core.sys.service.play_list;

import com.xtel.core.sys.model.play_list.DbGetListPlayListCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetListPlayListCmd extends AbsQueryParamRequestCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;
    private String phone_number;

    public GetListPlayListCmd(HttpServletRequest httpServletRequest, Integer page_index, Integer page_size,
                              String search_name, String order_by, String phone_number) {
        super(httpServletRequest);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
        this.phone_number = phone_number;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetListPlayListCmd dbCmd = new DbGetListPlayListCmd(transid, channel, page_index, page_size, search_name, order_by, phone_number);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
