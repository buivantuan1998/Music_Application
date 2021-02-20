package com.xtel.core.dto.response.play_list;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RandomSongInPlayList {
    @Column(name = "SONG_NAME")
    private String SONG_NAME;
    @Column(name = "URL")
    private String URL;
    @Column(name = "TIME")
    private Integer TIME;
    @Column(name = "SINGER_NAME")
    private String SINGER_NAME;
    @Column(name = "MUSICIAN_NAME")
    private String MUSICIAN_NAME;
    @Column(name = "CODE")
    private String CODE;
}
