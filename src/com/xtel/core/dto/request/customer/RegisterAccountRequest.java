package com.xtel.core.dto.request.customer;

import com.xtel.core.validator.NotNullOrEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterAccountRequest {
    @NotNullOrEmpty
    private String customer_name;
    @NotNullOrEmpty
    private String full_name;
    @NotNullOrEmpty
    private String email;
    @NotNullOrEmpty
    private String password;
    @NotNullOrEmpty
    private String phone_number;
}
