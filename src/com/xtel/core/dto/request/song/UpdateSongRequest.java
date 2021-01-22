package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateSongRequest extends InsertSongRequest{
    private Integer song_id;
}
