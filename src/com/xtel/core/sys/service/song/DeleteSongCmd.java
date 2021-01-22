package com.xtel.core.sys.service.song;


import com.xtel.core.dto.request.song.DeleteSongRequest;
import com.xtel.core.sys.model.song.DbDeleteSongCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

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
}
