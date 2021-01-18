package com.bkav.sdl.core.dto.response.song;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

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
    private Date RELASE_TIME;
    @Column(name = "TIME")
    private Float TIME;
    @Column(name = "SINGER_NAME")
    private String SINGER_NAME;
    @Column(name = "MUSICIAN_NAME")
    private String MUSICIAN_NAME;
    @Column(name = "CATEGORY_ID")
    private Integer CATEGORY_ID;
    @Column(name = "CREATE_TIME")
    private Date CREATE_TIME;
    @Column(name = "CREATE_BY")
    private String CREATE_BY;
    @Column(name = "UPDATE_BY")
    private String UPDATE_BY;
    @Column(name = "STATUS")
    private Integer STATUS;
}
