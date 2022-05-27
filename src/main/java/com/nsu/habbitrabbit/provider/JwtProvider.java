package com.nsu.habbitrabbit.provider;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    public final static String jwtSecret = "YPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDOR";

    public String generateToken(String email, int timeLife, String jwtSecret) {
        Date date = Date.from(LocalDate.now().plusDays(timeLife).atStartOfDay(ZoneId.systemDefault()).toInstant());
        UUID tokenId = UUID.randomUUID();
        var arg = Jwts.builder()
                .setId(tokenId.toString())
                .setSubject(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return arg;
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException unsEx) {
            return false;
        }
    }
}

