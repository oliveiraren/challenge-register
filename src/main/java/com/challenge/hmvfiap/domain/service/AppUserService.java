package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.domain.entity.User;
import com.challenge.hmvfiap.domain.repository.UserRepository;
import com.challenge.hmvfiap.domain.entity.JwtToken;
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

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }

    public String signUpUser(User user) {
        boolean userExistsCpf = userRepository
                .findByUserName(user.getUsername())
                .isPresent();

        if (userExistsCpf) {
            throw new IllegalStateException("CPF já cadastrado");
        }

        boolean userExistsEmail = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExistsEmail) {
            throw new IllegalStateException("E-mail já cadastrado");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        JwtToken confirmationJwtToken = new JwtToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationJwtToken);

        return token;
    }

    public void enableAppUser(String email) {
        userRepository.enableAppUser(email);
    }
}
