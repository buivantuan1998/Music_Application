package com.bkav.sdl.core.sys.entities;

import com.nvt.xpersistence.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@Table(name = "admin_account_cms")
public class AdminAccountCms {
    @ID
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_id")
    private Integer group_id;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "position")
    private String position;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name = "note")
    private String note;
    @Column(name = "create_time")
    @SkipUpdatable
    private Timestamp create_time;
    @SkipUpdatable
    @Column(name = "create_by")
    private Integer create_by;
    @Column(name = "update_time")
    @SkipInsertable
    private Timestamp update_time;
    @Column(name = "update_by")
    @SkipInsertable
    private Integer update_by;
    @Column(name = "is_owner")
    private Integer is_owner;
}


