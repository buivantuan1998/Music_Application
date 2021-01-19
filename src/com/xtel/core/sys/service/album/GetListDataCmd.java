package com.xtel.core.sys.service.album;

import com.xtel.core.sys.model.album.DbGetListDataCmd;
import com.xtel.core.sys.service.AbsQueryParamRequestCmd;

import javax.servlet.http.HttpServletRequest;

public class GetListDataCmd extends AbsQueryParamRequestCmd {
    public GetListDataCmd(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGetListDataCmd dbCmd = new DbGetListDataCmd(transid, channel);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getData());
    }

    @Override
    protected boolean isValidToken() {
        return true;
    }
}
