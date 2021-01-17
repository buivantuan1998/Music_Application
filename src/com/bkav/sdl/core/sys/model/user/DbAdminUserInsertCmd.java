package com.bkav.sdl.core.sys.model.user;

import com.bkav.sdl.core.dto.request.user.AdminUserInsertRequest;
import com.bkav.sdl.core.sys.entities.AdminAccountCms;
import com.bkav.sdl.core.sys.entities.AdminAccountRole;
import com.bkav.sdl.core.sys.entities.ConfigNotification;
import com.bkav.sdl.core.sys.model.PreparedStatementCommand;
import com.nvt.xpersistence.builder.MySqlQueryBuilder;

public class DbAdminUserInsertCmd extends PreparedStatementCommand {
    private AdminUserInsertRequest request;

    public DbAdminUserInsertCmd(String transid, String channel, AdminUserInsertRequest request) {
        super(transid, channel);
        this.request = request;
    }

    @Override
    protected void execute() throws Exception {
        MySqlQueryBuilder<AdminAccountCms> accQueryBuilder = new MySqlQueryBuilder<>(conn, AdminAccountCms.class);
        Integer id = Integer.parseInt(String.valueOf(accQueryBuilder.save(request.getAdminAccountCms())));

        request.getConfigNotification().setUser_id(id);
        MySqlQueryBuilder<ConfigNotification> configQueryBuilder = new MySqlQueryBuilder<>(conn, ConfigNotification.class);
        configQueryBuilder.save(request.getConfigNotification());

        for (AdminAccountRole userRole : request.getUserRoles()) {
            userRole.setAdmin_account_id(id);
            MySqlQueryBuilder<AdminAccountRole> roleQueryBuilder = new MySqlQueryBuilder<>(conn, AdminAccountRole.class);
            roleQueryBuilder.save(userRole);
        }

        code = 0;
        message = "SUCCESS";
    }
}
