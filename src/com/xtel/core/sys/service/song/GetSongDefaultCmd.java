package com.xtel.core.sys.service.song;

import com.xtel.core.sys.model.song.DbGetSongDefaultCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetSongDefaultCmd extends AbsQueryParamRequestCmd {
    private Integer customer_id;
    public GetSongDefaultCmd(HttpServletRequest httpServletRequest, Integer customer_id) {
        super(httpServletRequest);
        this.customer_id = customer_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetSongDefaultCmd dbCmd = new DbGetSongDefaultCmd(transid, channel, customer_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
