package com.challenge.hmvfiap.domain.service;

import com.challenge.hmvfiap.domain.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private final String chave = "$TQ(TQ$RQ)K(RQGKQAGFW";

    public String geraToken(Authentication authenticate) {
        AppUser appUser = (AppUser) authenticate.getPrincipal();
        Date hoje = new Date();
        int expiracao = 1800000;
        Date DataExpiracao = new Date(hoje.getTime() + expiracao);
        return Jwts.builder()
                .setSubject(appUser.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(DataExpiracao)
                .signWith(SignatureAlgorithm.HS512, chave)
                .compact();
    }

    public boolean isValido(String token) {
        try {
            Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long pegarIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(chave).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}