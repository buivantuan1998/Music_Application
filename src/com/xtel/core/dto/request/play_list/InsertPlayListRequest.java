package com.xtel.core.dto.request.play_list;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsertPlayListRequest {
    private String phone_number;
    private String play_list_name;
    private Integer type;

}
