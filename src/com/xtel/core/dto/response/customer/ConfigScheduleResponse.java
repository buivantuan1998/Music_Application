package com.xtel.core.dto.response.customer;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConfigScheduleResponse {
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "NAME_CONFIG_SCHEDULE")
    private String NAME_CONFIG_SCHEDULE;
    @Column(name = "START_HOUR")
    private Integer START_HOUR;
    @Column(name = "END_HOUR")
    private Integer END_HOUR;
    @Column(name = "START_MINUTE")
    private Integer START_MINUTE;
    @Column(name = "END_MINUTE")
    private Integer END_MINUTE;
    @Column(name = "STATUS")
    private Integer STATUS;
}
