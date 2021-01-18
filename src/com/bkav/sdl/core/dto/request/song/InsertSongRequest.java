package com.bkav.sdl.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsertSongRequest {
    private String song_name;
    private String url;
    private Double time;
    private String singer_name;
    private String musician_name;
    private Integer category_id;
    private String create_by;
}
