package com.bkav.sdl.core.sys.entities;

import com.nvt.xpersistence.annotation.Column;
import com.nvt.xpersistence.annotation.ID;
import com.nvt.xpersistence.annotation.Table;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@Table(name = "config_notification")
public class ConfigNotification {
    @ID
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "user_type")
    private Integer user_type;
    @Column(name = "transaction")
    private Integer transaction;
    @Column(name = "product")
    private Integer product;
    @Column(name = "customer")
    private Integer customer;
    @Column(name = "comment")
    private Integer comment;
    @Column(name = "post")
    private Integer post;
    @Column(name = "chat")
    private Integer chat;
    @Column(name = "create_time")
    private Timestamp create_time;
    @Column(name = "create_by")
    private Integer create_by;
    @Column(name = "update_time")
    private Timestamp update_time;
    @Column(name = "update_by")
    private Integer update_by;
}

