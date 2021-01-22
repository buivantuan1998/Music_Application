package com.xtel.core.dto.response.customer;

import com.xtel.core.dto.response.album.AlbumResponse;
import com.xtel.core.dto.response.play_list.PlayListResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GetDetailCustomer{
    private CustomerResponse customerResponse;
    private List<ConfigScheduleResponse> configScheduleResponseList;
    private AlbumResponse albumResponse;
    private List<PlayListResponse> playListResponseList;
}
