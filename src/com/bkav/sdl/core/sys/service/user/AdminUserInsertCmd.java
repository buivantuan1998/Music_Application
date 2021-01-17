package com.bkav.sdl.core.sys.service.user;

import com.bkav.sdl.core.dto.request.user.AdminUserInsertRequest;
import com.bkav.sdl.core.sys.model.user.DbAdminUserInsertCmd;
import com.bkav.sdl.core.sys.service.AbsBodyRequestCmd;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

public class AdminUserInsertCmd extends AbsBodyRequestCmd {
    private AdminUserInsertRequest request;

    public AdminUserInsertCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
        super(httpServletRequest, jsonRequest, classRequest);
    }

    @Override
    protected void executeCmd() throws Exception {
        request = getObject(AdminUserInsertRequest.class);
        request.getAdminAccountCms().setCreate_by(getIdFromToken());
        request.getAdminAccountCms().setCreate_time(new Timestamp(new Date().getTime()));

        request.getConfigNotification().setCreate_by(getIdFromToken());
        request.getConfigNotification().setCreate_time(new Timestamp(new Date().getTime()));

        DbAdminUserInsertCmd dbCmd = new DbAdminUserInsertCmd(transid, channel, request);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(), dbCmd.getMessage());

    }

}
