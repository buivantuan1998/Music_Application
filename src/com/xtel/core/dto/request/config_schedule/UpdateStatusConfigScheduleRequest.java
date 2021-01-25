package com.xtel.core.dto.request.config_schedule;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateStatusConfigScheduleRequest {
    private String phone_number;
    private Integer config_schedule_id;
    private Integer status;
}
