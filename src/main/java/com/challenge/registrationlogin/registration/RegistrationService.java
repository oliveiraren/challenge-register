package com.challenge.registrationlogin.registration;

import com.challenge.registrationlogin.appuser.AppUser;
import com.challenge.registrationlogin.appuser.AppUserRole;
import com.challenge.registrationlogin.appuser.AppUserService;
import com.challenge.registrationlogin.registration.token.ConfirmationToken;
import com.challenge.registrationlogin.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.isValidEmailAddress(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("E-mail inválido");
        }

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getCpf(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );

        return token;
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
