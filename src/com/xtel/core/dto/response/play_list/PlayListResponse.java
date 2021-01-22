package com.xtel.core.dto.response.play_list;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlayListResponse {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "PLAY_LIST_NAME")
    private String PLAY_LIST_NAME;
    @Column(name = "PLAY_LIST_TYPE")
    private Integer PLAY_LIST_TYPE;
}
