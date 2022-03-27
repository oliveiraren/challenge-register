package com.challenge.hmvfiap.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class EmailRegistrationDTO {
    private final String from = "challengehmvfiap@gmail.com";
    private final String to;
    private final String subject = "Cadastro Realizado Com Sucesso";
    private final String text;

    public EmailRegistrationDTO(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
