package com.bkav.sdl.core.sys.service.song;


import com.bkav.sdl.core.dto.request.song.DeleteSongRequest;
import com.bkav.sdl.core.sys.model.song.DbDeleteSongCmd;
import com.bkav.sdl.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeleteSongCmd extends AbsBodyRequestCmd {
  private DeleteSongRequest request;

    public DeleteSongCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(DeleteSongRequest.class);
        Integer song_id = request.getSong_id();

        DbDeleteSongCmd dbCmd = new DbDeleteSongCmd(transid, channel, song_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }

    @Override
    protected boolean isValidToken() {
        return  true;
    }
}
