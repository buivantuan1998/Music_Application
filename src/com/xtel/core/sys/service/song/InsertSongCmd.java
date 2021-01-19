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
        String song_name = request.getSong_name();
        String url = request.getUrl();
        Double time = request.getTime();
        String singer_name = request.getSinger_name();
        String musician_name = request.getMusician_name();
        Integer category_id = request.getCategory_id();
        String create_by = request.getCreate_by();

        DbInsertSongCmd dbCmd = new DbInsertSongCmd(transid, channel, song_name, url, time, singer_name, musician_name, category_id, create_by);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getSong_id());
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
