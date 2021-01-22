package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.UpdateSongRequest;
import com.xtel.core.sys.model.song.DbUpdateSongCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UpdateSongCmd extends AbsBodyRequestCmd {
    private UpdateSongRequest request;
    public UpdateSongCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(UpdateSongRequest.class);

        DbUpdateSongCmd dbCmd = new DbUpdateSongCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
