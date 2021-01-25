package com.xtel.core.dto.response.customer;

import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.dto.response.play_list.PlayListResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class DataAccount {
    private String token;
    private CustomerResponse customerResponse;
    private AlbumResponse albumResponse;
    private List<ConfigScheduleResponse> scheduleResponse;
    private List<PlayListResponse> playListResponseList;
}
