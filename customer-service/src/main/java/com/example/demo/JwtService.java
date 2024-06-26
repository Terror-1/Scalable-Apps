package com.example.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JwtService {

    private static final String jwtSecret = "d740b4e7547111cee19518ffef9b95645de3c346043281e52caaf7c48514e04b";

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String username,String email, String stripeId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("email",email);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(stripeId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

    }

    public static String getIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}