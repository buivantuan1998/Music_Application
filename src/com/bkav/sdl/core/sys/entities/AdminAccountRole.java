package com.bkav.sdl.core.sys.entities;

import com.nvt.xpersistence.annotation.Column;
import com.nvt.xpersistence.annotation.ID;
import com.nvt.xpersistence.annotation.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "admin_account_role")
public class AdminAccountRole {
    @Column(name = "id")
    @ID
    private Integer id;

    @Column(name = "admin_account_id")
    private Integer admin_account_id;

    @Column(name = "role_id")
    private Integer role_id;
}
