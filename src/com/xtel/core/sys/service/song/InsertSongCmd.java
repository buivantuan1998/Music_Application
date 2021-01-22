package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.InsertSongRequest;
import com.xtel.core.sys.model.song.DbInsertSongCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class InsertSongCmd extends AbsBodyRequestCmd {
    private InsertSongRequest request;
    public InsertSongCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertSongRequest.class);

        DbInsertSongCmd dbCmd = new DbInsertSongCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getSong_id());
    }
}
