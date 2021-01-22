package com.xtel.core.dto.response.config_schedule;

import com.xtel.core.dto.request.song.GetSongResponse;
import com.xtel.core.dto.response.customer.ConfigScheduleResponse;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetDetailConfigScheduleResponse {
    private ConfigScheduleResponse configScheduleResponse;
    private GetSongResponse songResponse;
}
