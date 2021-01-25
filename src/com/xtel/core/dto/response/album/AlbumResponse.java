package com.xtel.core.dto.response.album;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@ToString
public class AlbumResponse {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "ALBUM_NAME")
    private String ALBUM_NAME;
    @Column(name = "CREATE_TIME")
    private Timestamp CREATE_TIME;
    @Column(name = "CREATE_BY")
    private String CREATE_BY;
    @Column(name = "UPDATE_TIME")
    private Timestamp UPDATE_TIME;
    @Column(name = "UPDATE_BY")
    private String UPDATE_BY;
    @Column(name = "STATUS")
    private Integer STATUS;
}
