package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.DeleteSongInPlayListRequest;
import com.xtel.core.dto.request.song.DeleteSongRequest;
import com.xtel.core.sys.model.song.DbDeleteSongInPlayListCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeleteSongInPlayListCmd extends AbsBodyRequestCmd {
    private DeleteSongInPlayListRequest request;
    public DeleteSongInPlayListCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(DeleteSongInPlayListRequest.class);

        DbDeleteSongInPlayListCmd dbCmd = new DbDeleteSongInPlayListCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
