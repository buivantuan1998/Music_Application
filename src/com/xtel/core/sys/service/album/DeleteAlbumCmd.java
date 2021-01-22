package com.xtel.core.sys.service.album;

import com.xtel.core.dto.request.album.DeleteAlbumRequest;
import com.xtel.core.sys.model.album.DbDeleteAlbumCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class DeleteAlbumCmd extends AbsBodyRequestCmd {
    private DeleteAlbumRequest request;
    public DeleteAlbumCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(DeleteAlbumRequest.class);
        Integer customer_id = request.getCustomer_id();
        Integer album_id = request.getAlbum_id();

        DbDeleteAlbumCmd dbCmd = new DbDeleteAlbumCmd(transid, channel, customer_id, album_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
