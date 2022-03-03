package com.challenge.registrationlogin.appuser;

import com.challenge.registrationlogin.registration.token.ConfirmationToken;
import com.challenge.registrationlogin.registration.token.ConfirmationTokenService;
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
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "Cadastro não encontrado";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return appUserRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }

    public String signUpUser(AppUser appUser){
        boolean userExistsCpf = appUserRepository
                .findByCpf(appUser.getCpf())
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

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
