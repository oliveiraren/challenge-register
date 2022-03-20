package com.challenge.registrationlogin.dto;

public class JwtTokenDto {

    private String token;
    private String tipo;

    public JwtTokenDto(String token, String tipo) {
        super();
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
