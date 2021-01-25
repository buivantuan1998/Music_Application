package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteSongInPlayListRequest {
    private Integer play_list_id;
    private String code_song;
}
