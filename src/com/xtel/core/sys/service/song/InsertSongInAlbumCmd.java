package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.InsertSongInAlbumRequest;
import com.xtel.core.sys.model.song.DbDeleteSongInAlbumCmd;
import com.xtel.core.sys.model.song.DbInsertSongInAlbumCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class InsertSongInAlbumCmd extends AbsBodyRequestCmd {
    private InsertSongInAlbumRequest request;
    public InsertSongInAlbumCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertSongInAlbumRequest.class);

        DbInsertSongInAlbumCmd dbCmd = new DbInsertSongInAlbumCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
