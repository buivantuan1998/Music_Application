package com.bkav.sdl.core.dto.response.album;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
public class GetDataAlbum {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "CUSTOMER_ID")
    private Integer CUSTOMER_ID;
    @Column(name = "CUSTOMER_NAME")
    private String CUSTOMER_NAME;
    @Column(name = "ALBUM_NAME")
    private String ALBUM_NAME;
    @Column(name = "CREATE_TIME")
    private Date CREATE_TIME;
    @Column(name = "CREATE_BY")
    private String CREATE_BY;
    @Column(name = "UPDATE_TIME")
    private Date UPDATE_TIME;
    @Column(name = "UPDATE_BY")
    private String UPDATE_BY;
    @Column(name = "STATUS")
    private Integer STATUS;
}
