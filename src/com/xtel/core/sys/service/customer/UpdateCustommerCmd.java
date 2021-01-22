package com.xtel.core.sys.service.customer;

import com.xtel.core.dto.request.customer.UpdateAccountRequest;
import com.xtel.core.sys.model.customer.DbUpdateCustommerCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UpdateCustommerCmd extends AbsBodyRequestCmd {
    private UpdateAccountRequest request;
    public UpdateCustommerCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(UpdateAccountRequest.class);

        DbUpdateCustommerCmd dbCmd = new DbUpdateCustommerCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
