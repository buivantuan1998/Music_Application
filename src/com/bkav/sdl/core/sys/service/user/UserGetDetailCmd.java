package com.bkav.sdl.core.sys.service.user;

import com.bkav.sdl.core.sys.model.user.DbUserGetListCmd;
import com.bkav.sdl.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class UserGetDetailCmd extends AbsQueryParamRequestCmd {
    private Integer id;

    public UserGetDetailCmd(HttpServletRequest httpServletRequest, Integer id) {
        super(httpServletRequest);
        this.id = id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbUserGetListCmd dbCmd = new DbUserGetListCmd(transid, channel, id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getUserList());
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
