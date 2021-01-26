package com.xtel.core.sys.service.customer;

import com.xtel.core.sys.model.customer.DbGetListAccount;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetListAccount extends AbsQueryParamRequestCmd {
    public GetListAccount(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetListAccount dbCmd = new DbGetListAccount(transid, channel);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getCustomerResponseList());
    }
}
