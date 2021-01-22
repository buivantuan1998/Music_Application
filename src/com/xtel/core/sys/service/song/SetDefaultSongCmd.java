package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.SetDefaultSongRequest;
import com.xtel.core.sys.model.song.DbSetDefaultSongCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class SetDefaultSongCmd extends AbsBodyRequestCmd {
    private SetDefaultSongRequest request;
    public SetDefaultSongCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(SetDefaultSongRequest.class);
        Integer customer_id = request.getCustomer_id();
        Integer song_id = request.getSong_id();

        DbSetDefaultSongCmd dbCmd = new DbSetDefaultSongCmd(transid, channel, customer_id, song_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
