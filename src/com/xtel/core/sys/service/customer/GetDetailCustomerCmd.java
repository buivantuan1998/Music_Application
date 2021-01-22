package com.xtel.core.sys.service.customer;

import com.xtel.core.sys.model.customer.DbGetDetailCustomerCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailCustomerCmd extends AbsQueryParamRequestCmd {
    private Integer customer_id;

    public GetDetailCustomerCmd(HttpServletRequest httpServletRequest, Integer customer_id) {
        super(httpServletRequest);
        this.customer_id = customer_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailCustomerCmd dbCmd = new DbGetDetailCustomerCmd(transid, channel, customer_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
