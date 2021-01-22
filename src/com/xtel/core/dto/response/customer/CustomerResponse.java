package com.xtel.core.dto.response.customer;

import com.nvt.xpersistence.annotation.Column;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class CustomerResponse {
    @Column(name = "CUSTOMER_NAME")
    private String CUSTOMER_NAME;
    @Column(name = "EMAIL")
    private String EMAIL;
    @Column(name = "FULL_NAME")
    private String FULL_NAME;
    @Column(name = "CREATE_TIME")
    private Timestamp CREATE_TIME;
    @Column(name = "PHONE_NUMBER")
    private String PHONE_NUMBER;
}
