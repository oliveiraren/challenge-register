package com.challenge.hmvfiap.api.dto;

import lombok.Data;

@Data
public class RegistrationOutputDTO {
    private final boolean status = true;
    private String token;
}
