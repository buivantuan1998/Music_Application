package com.xtel.core.dto.request.play_list;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeletePlayListRequest {
    private String phone_number;
    private Integer play_list_id;
}
