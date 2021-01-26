package com.xtel.core.sys.service.play_list;

import com.xtel.core.dto.request.play_list.DeletePlayListRequest;
import com.xtel.core.sys.model.play_list.DbDeletePlayListCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeletePlayListCmd extends AbsBodyRequestCmd {
    private DeletePlayListRequest request;

    public DeletePlayListCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(DeletePlayListRequest.class);

        DbDeletePlayListCmd dbCmd = new DbDeletePlayListCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
