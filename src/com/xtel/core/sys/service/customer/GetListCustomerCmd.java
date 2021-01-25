package com.xtel.core.sys.service.customer;

import com.xtel.core.sys.model.customer.DbGetListCustomerCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetListCustomerCmd extends AbsQueryParamRequestCmd {
    private Integer page_index;
    private Integer page_size;
    private String search_name;
    private String order_by;

    public GetListCustomerCmd(HttpServletRequest httpServletRequest, Integer page_index, Integer page_size, String search_name, String order_by) {
        super(httpServletRequest);
        this.page_index = page_index;
        this.page_size = page_size;
        this.search_name = search_name;
        this.order_by = order_by;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetListCustomerCmd dbCmd = new DbGetListCustomerCmd(transid, channel, page_index, page_size, search_name, order_by);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
