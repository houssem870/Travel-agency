package com.ditraacademy.travel_agency.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {
    private final String JWT_SECRET = "DITRASECRET";

    public String generateToken (String username){
        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        return token;
    }
}
