package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.domain.entity.AppUser;
import com.challenge.hmvfiap.domain.entity.ConfirmationToken;
import com.challenge.hmvfiap.domain.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "Cadastro não encontrado";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return appUserRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExistsCpf = appUserRepository
                .findByUserName(appUser.getUsername())
                .isPresent();

        if (userExistsCpf) {
            throw new IllegalStateException("CPF já cadastrado");
        }

        boolean userExistsEmail = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExistsEmail) {
            throw new IllegalStateException("E-mail já cadastrado");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationConfirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationConfirmationToken);

        return token;
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }
}
