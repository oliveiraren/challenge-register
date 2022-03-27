package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.domain.entity.JwtToken;
import com.challenge.hmvfiap.domain.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(JwtToken jwtToken) {
        confirmationTokenRepository.save(jwtToken);
    }

    public Optional<JwtToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository
                .updateConfirmedAt(
                        token, LocalDateTime.now());
    }
}
