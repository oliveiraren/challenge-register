package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.domain.entity.JwtToken;
import com.challenge.hmvfiap.domain.repository.JwtTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final JwtTokenRepository jwtTokenRepository;

    public void saveConfirmationToken(JwtToken jwtToken) {
        jwtTokenRepository.save(jwtToken);
    }

    public Optional<JwtToken> getToken(String token) {
        return jwtTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        jwtTokenRepository
                .updateConfirmedAt(
                        token, LocalDateTime.now());
    }
}
