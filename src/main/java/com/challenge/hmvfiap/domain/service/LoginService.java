package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.domain.entity.User;
import com.challenge.hmvfiap.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User buscarPorId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário #{username} não encontrado"));
    }
}
