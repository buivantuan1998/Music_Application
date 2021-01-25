package com.xtel.core.sys.service.customer;

import com.xtel.core.dto.request.customer.DeleteCustomerRequest;
import com.xtel.core.sys.model.customer.DbDeleteCustomerCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeleteCustomerCmd extends AbsBodyRequestCmd {
    public DeleteCustomerCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        DeleteCustomerRequest request = getObject(DeleteCustomerRequest.class);

        DbDeleteCustomerCmd dbCmd = new DbDeleteCustomerCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
