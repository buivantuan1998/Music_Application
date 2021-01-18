package com.bkav.sdl.core.sys.service.album;

import com.bkav.sdl.core.sys.model.album.DbGetDetailAlbumCmd;
import com.bkav.sdl.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailAlbumCmd extends AbsQueryParamRequestCmd {
    private Integer album_id;
    private Integer customer_id;

    public GetDetailAlbumCmd(HttpServletRequest httpServletRequest, Integer album_id, Integer customer_id) {
        super(httpServletRequest);
        this.album_id = album_id;
        this.customer_id = customer_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailAlbumCmd dbCmd = new DbGetDetailAlbumCmd(transid, channel, album_id, customer_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
