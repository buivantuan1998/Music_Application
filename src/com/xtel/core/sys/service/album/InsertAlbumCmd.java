package com.xtel.core.sys.service.album;

import com.xtel.core.dto.request.album.InsertAlbumRequest;
import com.xtel.core.sys.model.album.DbInsertAlbumCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class InsertAlbumCmd extends AbsBodyRequestCmd {
    private InsertAlbumRequest request;
    public InsertAlbumCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertAlbumRequest.class);
        Integer customer_id = request.getCustomer_id();
        String create_by = request.getCreate_by();
        String album_name = request.getAlbum_name();

        DbInsertAlbumCmd dbCmd = new DbInsertAlbumCmd(transid, channel, customer_id, create_by, album_name);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getAlbum_id());
    }
}
