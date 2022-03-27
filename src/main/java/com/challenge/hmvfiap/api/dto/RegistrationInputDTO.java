package com.challenge.hmvfiap.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationInputDTO {
    private final String fullName;
    private final String userName;
    private final String email;
    private final String password;
}
