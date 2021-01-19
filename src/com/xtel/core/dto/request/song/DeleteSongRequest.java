package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteSongRequest {
    private Integer song_id;
}
