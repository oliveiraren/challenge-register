package com.challenge.registrationlogin.controller;

import com.challenge.registrationlogin.registration.RegistrationRequest;
import com.challenge.registrationlogin.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrarionController {

    private final RegistrationService registrationService;

    @PostMapping
    public Object register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
