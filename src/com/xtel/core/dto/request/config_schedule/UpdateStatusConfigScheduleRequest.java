package com.xtel.core.dto.request.config_schedule;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateStatusConfigScheduleRequest {
    private Integer customer_id;
    private Integer config_schedule_id;
    private Integer status;
}
