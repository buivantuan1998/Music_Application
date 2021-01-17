package com.bkav.sdl.core.sys.service.notification;

import com.bkav.sdl.core.sys.model.notification.DbNotifyGetListCmd;
import com.bkav.sdl.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class NotifyGetListCmd extends AbsQueryParamRequestCmd {
    private Integer user_id;
    public NotifyGetListCmd(HttpServletRequest httpServletRequest, Integer user_id) {
        super(httpServletRequest);
        this.user_id = user_id;

    }

    @Override
    protected void executeCmd() throws Exception {

        DbNotifyGetListCmd dbCmd = new DbNotifyGetListCmd(transid, channel, user_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getNotifyList());
    }
    
    @Override
    public boolean isValidToken(){return true; }
}
