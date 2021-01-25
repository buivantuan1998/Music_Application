package com.xtel.core.dto.response.song;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class SongResponse {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "SONG_NAME")
    private String SONG_NAME;
    @Column(name = "URL")
    private String URL;
    @Column(name = "VIEWS")
    private Integer VIEWS;
    @Column(name = "RELASE_TIME")
    private Timestamp RELASE_TIME;
    @Column(name = "TIME")
    private Integer TIME;
    @Column(name = "SINGER_NAME")
    private String SINGER_NAME;
    @Column(name = "MUSICIAN_NAME")
    private String MUSICIAN_NAME;
    @Column(name = "CATEGORY_ID")
    private Integer CATEGORY_ID;
    @Column(name = "CATEGORY_NAME")
    private String CATEGORY_NAME;
    @Column(name = "CREATE_TIME")
    private Timestamp CREATE_TIME;
    @Column(name = "CREATE_BY")
    private String CREATE_BY;
    @Column(name = "UPDATE_BY")
    private String UPDATE_BY;
    @Column(name = "STATUS")
    private Integer STATUS;
    @Column(name = "CODE")
    private String CODE;
}
