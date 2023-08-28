package com.pedrofacchinetti.TesteBackEnd.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final String JWT_SECRET = "YOUR_SECRET_HERE_YOUR_SECRET_HERE_YOUR_SECRET_HERE"; 
    private final long JWT_EXPIRATION = 3600000L;  // Token expira em 1 hora
    private final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    public String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getClaimsFromToken(token).get("role");
    }
}
