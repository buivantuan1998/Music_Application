package com.xtel.core.dto.request.config_schedule;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateConfigScheduleRequest extends InsertConfigScheduleRequest{
    private Integer config_schedule_id;
}
