package com.xtel.core.dto.request.customer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateAccountRequest {
    private Integer customer_id;
    private String customer_name;
}
