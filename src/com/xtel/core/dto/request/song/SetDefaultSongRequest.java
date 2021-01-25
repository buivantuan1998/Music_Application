package com.xtel.core.dto.request.song;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SetDefaultSongRequest {
    private String code_song;
    private String phone_number;
}
