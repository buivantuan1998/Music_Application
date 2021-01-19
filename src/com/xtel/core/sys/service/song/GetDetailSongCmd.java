package com.xtel.core.sys.service.song;

import com.xtel.core.sys.model.song.DbGetDetailSongCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailSongCmd extends AbsQueryParamRequestCmd {
    private Integer song_id;

    public GetDetailSongCmd(HttpServletRequest httpServletRequest, Integer song_id) {
        super(httpServletRequest);
        this.song_id = song_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailSongCmd dbCmd = new DbGetDetailSongCmd(transid, channel, song_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
