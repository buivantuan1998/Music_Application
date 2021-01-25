package com.xtel.core.dto.response.album;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetListSongInAlbum {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "SONG_NAME")
    private String SONG_NAME;
    @Column(name = "URL")
    private String URL;
    @Column(name = "SINGER_NAME")
    private String SINGER_NAME;
    @Column(name = "MUSICIAN_NAME")
    private String MUSICIAN_NAME;
    @Column(name = "CODE")
    private String CODE;
    @Column(name = "CATEGORY_ID")
    private Integer CATEGORY_ID;
    @Column(name = "CATEGORY_NAME")
    private String CATEGORY_NAME;
    @Column(name = "STATUS")
    private Integer STATUS;
}
