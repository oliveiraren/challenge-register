package com.challenge.registrationlogin.controller;

import com.challenge.registrationlogin.registration.RegistrationRequest;
import com.challenge.registrationlogin.registration.RegistrationService;
import com.challenge.registrationlogin.security.config.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrarionController {

    private final RegistrationService registrationService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;


    @PostMapping
    public Object register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
