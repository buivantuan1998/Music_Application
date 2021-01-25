package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsertSongInAlbumRequest {
    private Integer album_id;
    private String code_song;
}
