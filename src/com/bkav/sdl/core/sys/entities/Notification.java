package com.bkav.sdl.core.sys.entities;

import com.nvt.xpersistence.annotation.Column;
import com.nvt.xpersistence.annotation.ID;
import com.nvt.xpersistence.annotation.SkipUpdatable;
import com.nvt.xpersistence.annotation.Table;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@Table(name = "sdl_enduser.notification")
public class Notification {
@ID
@Column(name = "notification_id")
private Integer notification_id;
@Column(name = "type")
private Integer type;
@Column(name = "at")
@SkipUpdatable
private Timestamp at;
@Column(name = "title")
private String title;
@Column(name = "content")
private String content;
@Column(name = "status")
private Integer status;
@Column(name = "comment_id")
private Integer comment_id;
@Column(name = "user_id")
private Integer user_id;
@Column(name = "url")
private String url;
}