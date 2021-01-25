package com.xtel.core.dto.response.play_list;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class PlayListData {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "PLAY_LIST_NAME")
    private String PLAY_LIST_NAME;
    @Column(name = "PLAY_LIST_TYPE")
    private Integer PLAY_LIST_TYPE;
    @Column(name = "CREATE_TIME")
    private Timestamp CREATE_TIME;
    @Column(name = "CREATE_BY")
    private String CREATE_BY;
    @Column(name = "UPDATE_TIME")
    private Timestamp UPDATE_TIME;
    @Column(name = "UPDATE_BY")
    private String UPDATE_BY;
    @Column(name = "STATUS")
    private Integer STATUS;
}
