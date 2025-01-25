package com.yourcompany.ecommerce.Util;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "za12Ef91ErAsd@91Qwe!KdjWeCmUIoy31QwXraLpL08ZVp8"; // Use your secret key
    private static final long EXPIRATION_TIME = 3600000 * 24 * 365; // 1 Year in milliseconds

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate a JWT token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate a JWT token
    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return extractedUsername.equals(username);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract claims (like userId) from a JWT token
    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract a specific claim (e.g., userId) from a JWT token
    public static String extractUserId(String token) {
        return extractClaims(token).get("userId", String.class);
    }
}

