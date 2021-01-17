package com.bkav.sdl.core.sys.entities;

import com.nvt.xpersistence.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@Table(name = "sdl_enduser.customer_account")
public class CustomerAccount {
    @ID
    @Column(name = "id")
    private Integer id;
    @Column(name = "cust_id")
    private Integer cust_id;
    @Column(name = "account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "account_type")
    private Integer account_type;
    @Column(name = "status")
    private Integer status;
    @Column(name = "last_login")
    private Timestamp last_login;
    @Column(name = "create_time")
    @SkipUpdatable
    private Timestamp create_time;
    @Column(name = "create_by")
    @SkipUpdatable
    private String create_by;
    @Column(name = "update_time")
    @SkipInsertable
    private Timestamp update_time;
    @Column(name = "update_by")
    @SkipInsertable
    private String update_by;
}


