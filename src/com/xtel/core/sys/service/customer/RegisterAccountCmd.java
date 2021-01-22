package com.xtel.core.sys.service.customer;

import com.xtel.core.dto.request.customer.RegisterAccountRequest;
import com.xtel.core.sys.model.customer.DbRegisterAccountCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class RegisterAccountCmd extends AbsBodyRequestCmd {
    private RegisterAccountRequest request;

    public RegisterAccountCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(RegisterAccountRequest.class);

        DbRegisterAccountCmd dbCmd = new DbRegisterAccountCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getCustomer_id());
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
