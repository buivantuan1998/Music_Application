package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.DeleteSongInAlbumRequest;
import com.xtel.core.sys.model.song.DbDeleteSongInAlbumCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeleteSongInAlbumCmd extends AbsBodyRequestCmd {
    private DeleteSongInAlbumRequest request;
    public DeleteSongInAlbumCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(DeleteSongInAlbumRequest.class);

        DbDeleteSongInAlbumCmd dbCmd = new DbDeleteSongInAlbumCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
