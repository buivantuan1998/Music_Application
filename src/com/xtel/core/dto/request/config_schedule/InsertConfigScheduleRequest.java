package com.xtel.core.dto.request.config_schedule;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsertConfigScheduleRequest {
    private String name;
    private Integer start_hour;
    private Integer end_hour;
    private Integer start_minute;
    private Integer end_minute;
    private String phone_number;
    private Integer status;
}
