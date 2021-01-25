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
        String code_song = request.getCode_song();
        String phone_number = request.getPhone_number();

        DbSetDefaultSongCmd dbCmd = new DbSetDefaultSongCmd(transid, channel, code_song, phone_number);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());
    }
}
