package com.xtel.core.sys.service.song;

import com.xtel.core.dto.request.song.InsertSongInPlayListRequest;
import com.xtel.core.sys.model.song.DbInsertSongInPlayListCmd;
import com.xtel.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class InsertSongInPlayListCmd extends AbsBodyRequestCmd {
    private InsertSongInPlayListRequest request;
    public InsertSongInPlayListCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(InsertSongInPlayListRequest.class);

        DbInsertSongInPlayListCmd dbCmd = new DbInsertSongInPlayListCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
