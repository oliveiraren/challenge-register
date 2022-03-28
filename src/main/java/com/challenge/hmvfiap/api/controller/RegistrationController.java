package com.challenge.hmvfiap.api.controller;

import com.challenge.hmvfiap.api.dto.RegistrationInputDTO;
import com.challenge.hmvfiap.domain.service.JwtTokenService;
import com.challenge.hmvfiap.domain.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;


    @PostMapping
    public Object register(@RequestBody RegistrationInputDTO request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
