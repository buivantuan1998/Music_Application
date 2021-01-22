package com.xtel.core.sys.entities;

import com.nvt.xpersistence.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@Table(name = "song")
public class Song {
    @ID(auto_increment="song_SEQ")
    @Column(name = "ID")
    private Long id;
    @Column(name = "SONG_NAME")
    private String song_name;
    @Column(name = "URL")
    private String url;
    @Column(name = "VIEWS")
    private Long views;
    @Column(name = "RELASE_TIME")
    private Timestamp relase_time;
    @Column(name = "TIME")
    private Long time;
    @Column(name = "SINGER_NAME")
    private String singer_name;
    @Column(name = "MUSICIAN_NAME")
    private String musician_name;
    @Column(name = "CATEGORY_ID")
    private Long category_id;
    @Column(name = "CREATE_TIME")
    private Timestamp create_time;
    @Column(name = "CREATE_BY")
    private String create_by;
    @Column(name = "UPDATE_TIME")
    private Timestamp update_time;
    @Column(name = "UPDATE_BY")
    private String update_by;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "PLAY_LIST_ID")
    private Long play_list_id;
    @Column(name = "CUSTOMER_ID")
    private Long customer_id;
    @Column(name = "ALBUM_ID")
    private Long album_id;
    @Column(name = "IS_DEFAULT")
    private Long is_default;
}
