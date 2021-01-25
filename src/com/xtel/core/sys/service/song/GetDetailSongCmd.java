package com.xtel.core.sys.service.song;

import com.xtel.core.sys.model.song.DbGetDetailSongCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailSongCmd extends AbsQueryParamRequestCmd {
    private String code_song;

    public GetDetailSongCmd(HttpServletRequest httpServletRequest, String code_song) {
        super(httpServletRequest);
        this.code_song = code_song;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailSongCmd dbCmd = new DbGetDetailSongCmd(transid, channel, code_song);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
