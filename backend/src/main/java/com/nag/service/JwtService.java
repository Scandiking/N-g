package com.nag.service;

/**
 * @description
 * @author Hinkula (Kristian)
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;


@Component
public class JwtService {
    static final long EXPIRATION_TIME = 86400000; // 1 døgn i millisekunder, burde være kortere i produksjon,
    static final String PREFIX = "Bearer";

    // Generate secret key. Only for demonstation purposes.
    // In production, you should read it from the application configuration.
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Genereate signed JTW token
    public String getToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

        return token;
    }

    // Get a token from request authorization header,
    // verify the token, and get username
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String user = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return user;
        }
        return null;
    }
}
