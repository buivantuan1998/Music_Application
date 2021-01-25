package com.xtel.core.dto.request.customer;

import com.sun.scenario.effect.impl.prism.PrImage;
import com.xtel.core.validator.NotNullOrEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginAccountRequest {
    @NotNullOrEmpty
    private String phone_number;
    @NotNullOrEmpty
    private String password;

    private int remember;
}
