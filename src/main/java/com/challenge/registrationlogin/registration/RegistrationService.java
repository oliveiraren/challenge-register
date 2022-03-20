package com.challenge.registrationlogin.registration;

import com.challenge.registrationlogin.appuser.AppUser;
import com.challenge.registrationlogin.appuser.AppUserRole;
import com.challenge.registrationlogin.appuser.AppUserService;
import com.challenge.registrationlogin.email.EmailRegistration;
import com.challenge.registrationlogin.registration.EmailValidator;
import com.challenge.registrationlogin.registration.RegistrationRequest;
import com.challenge.registrationlogin.config.RabbitMQConfig;
import com.challenge.registrationlogin.registration.token.ConfirmationToken;
import com.challenge.registrationlogin.registration.token.ConfirmationTokenService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final RabbitTemplate template;
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public Object register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.isValidEmailAddress(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("E-mail inválido");
        }

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFullName(),
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );

        String link = "Para confirmar seu cadastro clique no link a seguir: https://challenge-registration.herokuapp.com/api/registration/confirm?token="
                + token;

        EmailRegistration emailRegistration = new EmailRegistration(
                request.getEmail(),
                link );

        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, emailRegistration);

        RegistrationResponse registrationResponse = new RegistrationResponse(
                token
        );

        return registrationResponse;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token não encontrado"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("E-mail já confirmado");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expirado");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "Confirmado";
    }

}
