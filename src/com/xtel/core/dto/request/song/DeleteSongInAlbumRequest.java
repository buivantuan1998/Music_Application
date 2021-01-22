package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteSongInAlbumRequest {
    private Integer album_id;
    private Integer song_id;
}
