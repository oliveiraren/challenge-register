package com.challenge.hmvfiap.core.filter;

import com.challenge.hmvfiap.domain.entity.AppUser;
import com.challenge.hmvfiap.domain.service.JwtTokenService;
import com.challenge.hmvfiap.domain.service.LoginService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService tokenService;
    private final LoginService loginService;

    public JwtTokenFilter(JwtTokenService tokenService, LoginService loginService) {
        this.tokenService = tokenService;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperaToken(request);
        if (tokenService.isValido(token)) {
            autorizaUsuario(token);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperaToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }

    private void autorizaUsuario(String token) {
        Long id = tokenService.pegarIdUsuario(token);
        AppUser appUser = loginService.buscarPorId(id);
        Authentication authentication = new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
