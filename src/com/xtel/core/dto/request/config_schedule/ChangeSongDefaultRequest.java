package com.xtel.core.dto.request.config_schedule;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChangeSongDefaultRequest {
    private String code_song;
    private String phone_number;
    private Integer config_schedule_id;
}
