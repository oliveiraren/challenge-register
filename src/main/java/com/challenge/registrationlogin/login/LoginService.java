package com.challenge.registrationlogin.login;

import com.challenge.registrationlogin.appuser.AppUser;
import com.challenge.registrationlogin.appuser.AppUserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LoginService {

    private final AppUserRepository appUserRepository;

    public LoginService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser buscarPorId(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário #{username} não encontrado"));
    }
}
