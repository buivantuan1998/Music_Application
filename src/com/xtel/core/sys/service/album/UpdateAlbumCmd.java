package com.xtel.core.sys.service.album;

import com.xtel.core.dto.request.album.UpdateAlbumRequest;
import com.xtel.core.sys.model.album.DbUpdateAlbumCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UpdateAlbumCmd extends AbsBodyRequestCmd {
    private UpdateAlbumRequest request;

    public UpdateAlbumCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(UpdateAlbumRequest.class);

        DbUpdateAlbumCmd dbCmd = new DbUpdateAlbumCmd(transid, channel, request, getEmailFromToken());
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
