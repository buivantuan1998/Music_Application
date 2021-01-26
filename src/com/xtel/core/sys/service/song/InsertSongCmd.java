package com.xtel.core.sys.service.song;

import com.xtel.core.common.AutoSendMail;
import com.xtel.core.common.ContentMail;
import com.xtel.core.common.TitleMail;
import com.xtel.core.dto.request.song.InsertSongRequest;
import com.xtel.core.dto.response.customer.CustomerResponse;
import com.xtel.core.sys.model.customer.DbGetListAccount;
import com.xtel.core.sys.model.song.DbGetListDataCmd;
import com.xtel.core.sys.model.song.DbInsertSongCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class InsertSongCmd extends AbsBodyRequestCmd {
    private InsertSongRequest request;
    public InsertSongCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertSongRequest.class);

        DbInsertSongCmd dbCmd = new DbInsertSongCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getSong_id());

        DbGetListAccount dbGetListAccount = new DbGetListAccount(transid, channel);
        executeDbCmd(dbGetListAccount);
        List<CustomerResponse> customerResponseList = dbGetListAccount.getCustomerResponseList();
        for (CustomerResponse customerResponse : customerResponseList) {
            AutoSendMail.sendMail(customerResponse.getEMAIL(), ContentMail.CONTENT_SONG_NEW, TitleMail.TITLE_SONG_NEW);
        }
    }
}
