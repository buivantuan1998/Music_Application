package com.xtel.core.sys.service.play_list;

import com.xtel.core.sys.model.play_list.DbGetDetailPlayListCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetDetailPlayListCmd extends AbsQueryParamRequestCmd {
    private Integer play_list_id;
    private String phone_number;

    public GetDetailPlayListCmd(HttpServletRequest httpServletRequest, Integer play_list_id, String phone_number) {
        super(httpServletRequest);
        this.play_list_id = play_list_id;
        this.phone_number = phone_number;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetDetailPlayListCmd dbCmd = new DbGetDetailPlayListCmd(transid, channel, play_list_id, phone_number);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }
}
