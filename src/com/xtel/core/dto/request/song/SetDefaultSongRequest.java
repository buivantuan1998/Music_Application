package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SetDefaultSongRequest {
    private Integer customer_id;
    private Integer song_id;
}
