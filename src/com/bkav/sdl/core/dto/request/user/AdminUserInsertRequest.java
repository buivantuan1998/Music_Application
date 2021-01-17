package com.bkav.sdl.core.dto.request.user;

import com.bkav.sdl.core.sys.entities.AdminAccountCms;
import com.bkav.sdl.core.sys.entities.AdminAccountRole;
import com.bkav.sdl.core.sys.entities.ConfigNotification;
import com.bkav.sdl.core.validator.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AdminUserInsertRequest {
    @NotNull
    private AdminAccountCms adminAccountCms;
    @NotNull
    private ConfigNotification configNotification;
    @NotNull
    private List<AdminAccountRole> userRoles;
}
