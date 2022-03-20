package com.challenge.registrationlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class JwtTokenDto {

    private String token;
    private String tipo;
    private Long id;
    private String name;
    private String email;

}
