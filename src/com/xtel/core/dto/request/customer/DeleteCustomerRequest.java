package com.xtel.core.dto.request.customer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeleteCustomerRequest {
    private Integer customer_id;
}
