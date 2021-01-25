package com.xtel.core.sys.service.song;

import com.xtel.core.sys.model.song.DbGetSongDefaultCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetSongDefaultCmd extends AbsQueryParamRequestCmd {
    private String phone_number;
    public GetSongDefaultCmd(HttpServletRequest httpServletRequest, String phone_number) {
        super(httpServletRequest);
        this.phone_number = phone_number;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetSongDefaultCmd dbCmd = new DbGetSongDefaultCmd(transid, channel, phone_number);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
