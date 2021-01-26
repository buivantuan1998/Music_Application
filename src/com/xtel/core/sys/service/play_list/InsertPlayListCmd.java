package com.xtel.core.sys.service.play_list;

import com.xtel.core.dto.request.play_list.InsertPlayListRequest;
import com.xtel.core.sys.model.play_list.DbInsertPlayListCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class InsertPlayListCmd extends AbsBodyRequestCmd {
    private InsertPlayListRequest request;
    public InsertPlayListCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertPlayListRequest.class);

        DbInsertPlayListCmd dbCmd = new DbInsertPlayListCmd(transid, channel, request, getEmailFromToken());
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getPlay_list_id());
    }
}
