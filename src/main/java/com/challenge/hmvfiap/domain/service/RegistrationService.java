package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.api.dto.RegistrationInputDTO;
import com.challenge.hmvfiap.api.dto.RegistrationOutputDTO;
import com.challenge.hmvfiap.domain.entity.User;
import com.challenge.hmvfiap.domain.enums.UserRole;
import com.challenge.hmvfiap.core.config.RabbitMQConfig;
import com.challenge.hmvfiap.api.dto.EmailRegistrationDTO;
import com.challenge.hmvfiap.domain.validator.EmailValidator;
import com.challenge.hmvfiap.domain.entity.JwtToken;
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

    public Object register(RegistrationInputDTO request) {

        boolean isValidEmail = EmailValidator.isValidEmailAddress(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("E-mail inválido");
        }

        String token = appUserService.signUpUser(
                new User(
                        request.getFullName(),
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );

        String link = "Para confirmar seu cadastro clique no link a seguir: https://challenge-registration.herokuapp.com/api/registration/confirm?token="
                + token;

        EmailRegistrationDTO emailRegistration = new EmailRegistrationDTO(
                request.getEmail(),
                link);

        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, emailRegistration);

        return new RegistrationOutputDTO(
                token
        );
    }

    @Transactional
    public String confirmToken(String token) {
        JwtToken confirmationJwtToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token não encontrado"));

        if (confirmationJwtToken.getConfirmedAt() != null) {
            throw new IllegalStateException("E-mail já confirmado");
        }

        LocalDateTime expiredAt = confirmationJwtToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expirado");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationJwtToken.getUser().getEmail());
        return "Confirmado";
    }

}
