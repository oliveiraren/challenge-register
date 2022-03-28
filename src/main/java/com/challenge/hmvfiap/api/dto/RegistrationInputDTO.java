package com.challenge.hmvfiap.api.dto;

import lombok.Data;

@Data
public class RegistrationInputDTO {
    private final String fullName;
    private final String userName;
    private final String email;
    private final String password;
}
