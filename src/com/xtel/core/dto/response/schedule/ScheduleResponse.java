package com.xtel.core.dto.response.schedule;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ScheduleResponse {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "PLAY_HOUR")
    private String PLAY_HOUR;
    @Column(name = "PLAY_DATE")
    private Integer PLAY_DATE;
}
