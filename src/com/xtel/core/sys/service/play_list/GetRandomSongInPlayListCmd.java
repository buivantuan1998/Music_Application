package com.xtel.core.sys.service.play_list;

import com.xtel.core.sys.model.play_list.DbGetRandomSongInPlayListCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetRandomSongInPlayListCmd extends AbsQueryParamRequestCmd {
    private String phone_number;
    private Integer play_list_id;

    public GetRandomSongInPlayListCmd(HttpServletRequest httpServletRequest, String phone_number, Integer play_list_id) {
        super(httpServletRequest);
        this.phone_number = phone_number;
        this.play_list_id = play_list_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetRandomSongInPlayListCmd dbCmd = new DbGetRandomSongInPlayListCmd(transid, channel, phone_number, play_list_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
