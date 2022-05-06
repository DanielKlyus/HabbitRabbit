package com.nsu.habbitrabbit.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    public String generateToken(String email, int timeLife, String jwtSecret) {
        Date date = Date.from(LocalDate.now().plusDays(timeLife).atStartOfDay(ZoneId.systemDefault()).toInstant());
        UUID tokenId = UUID.randomUUID();
        return Jwts.builder()
                .setId(tokenId.toString())
                .setSubject(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
