package com.nsu.habbitrabbit.provider;

import com.nsu.habbitrabbit.domain.Credentials;
import com.nsu.habbitrabbit.domain.Player;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    public final static String jwtSecret = "YPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDOR";

    public String generateToken(Player player, int timeLife, String jwtSecret) {
        Date date = Date.from(LocalDate.now().plusDays(timeLife).atStartOfDay(ZoneId.systemDefault()).toInstant());
        UUID tokenId = UUID.randomUUID();
        var arg = Jwts.builder()
                .claim("id", player.getId())
                .claim("email", player.getEmail())
                .claim("admin", player.isAdmin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return arg;
    }

    public Credentials parseToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
        Integer id = jwsClaims.getBody().get("id", Integer.class);
        String email = jwsClaims.getBody().get("email", String.class);
        boolean isAdmin = jwsClaims.getBody().get("admin", Boolean.class);

        return new Credentials(email, id, isAdmin);
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

