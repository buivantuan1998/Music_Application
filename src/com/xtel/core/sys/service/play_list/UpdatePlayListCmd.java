package com.xtel.core.sys.service.play_list;

import com.xtel.core.dto.request.play_list.UpdatePlayListRequest;
import com.xtel.core.sys.model.play_list.DbUpdatePlayListCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UpdatePlayListCmd extends AbsBodyRequestCmd {
    private UpdatePlayListRequest request;
    public UpdatePlayListCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(UpdatePlayListRequest.class);

        DbUpdatePlayListCmd dbCmd = new DbUpdatePlayListCmd(transid, channel, request, getEmailFromToken());
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
