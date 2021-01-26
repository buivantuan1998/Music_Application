package com.xtel.core.sys.service.customer;

import com.xtel.core.dto.request.customer.RegisterAccountRequest;
import com.xtel.core.sys.model.customer.DbRegisterAccountCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

import static com.xtel.core.common.AutoSendMail.sendMail;
import static com.xtel.core.common.ContentMail.CONTENT_REGISTER_ACCOUNT;
import static com.xtel.core.common.TitleMail.TITLE_REGISTER;

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

        sendMail(request.getEmail(), CONTENT_REGISTER_ACCOUNT, TITLE_REGISTER);
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
