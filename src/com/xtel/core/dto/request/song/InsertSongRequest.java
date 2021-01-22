package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class InsertSongRequest {
    private String song_name;
    private String url;
    private Timestamp relase_time;
    private Integer time;
    private String singer_name;
    private String musician_name;
    private Integer category_id;
    private String create_by;
}
