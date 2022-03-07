package com.challenge.registrationlogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/")
public class TestaLoginController {

    @GetMapping("testa-login")
    public String login() {
        return "Logado!";
    }
}
