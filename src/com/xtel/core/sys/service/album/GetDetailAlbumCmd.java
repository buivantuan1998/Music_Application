package com.xtel.core.sys.service.album;

import com.xtel.core.sys.model.album.DbGetDetailAlbumCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailAlbumCmd extends AbsQueryParamRequestCmd {
    private Integer album_id;
    private String phone_number;

    public GetDetailAlbumCmd(HttpServletRequest httpServletRequest, Integer album_id, String phone_number) {
        super(httpServletRequest);
        this.album_id = album_id;
        this.phone_number = phone_number;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailAlbumCmd dbCmd = new DbGetDetailAlbumCmd(transid, channel, album_id, phone_number);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
