package com.challenge.hmvfiap.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDTO {

    private String token;
    private String tipo;
    private Long id;
    private String name;
    private String email;

}
