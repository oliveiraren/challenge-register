package com.challenge.registrationlogin.email;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class EmailRegistration {
    private final String from = "challengehmvfiap@gmail.com";
    private final String to;
    private final String subject = "Cadastro Realizado Com Sucesso";
    private final String text;

    public EmailRegistration(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
