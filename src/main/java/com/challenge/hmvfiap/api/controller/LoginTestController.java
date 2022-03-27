package com.challenge.hmvfiap.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/")
public class LoginTestController {

    @GetMapping("testa-login")
    public String login() {
        return "Logado!";
    }
}
