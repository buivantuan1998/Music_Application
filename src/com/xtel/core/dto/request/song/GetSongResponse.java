package com.xtel.core.dto.request.song;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetSongResponse {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "SONG_NAME")
    private String SONG_NAME;
    @Column(name = "URL")
    private String URL;
    @Column(name = "CODE")
    private String CODE;
}
