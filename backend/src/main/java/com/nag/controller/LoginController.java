package com.nag.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import com.nag.model.AccountCredentials;
import com.nag.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author Hinkula (Krisetian)
 */

@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        Authentication auth = authenticationManager.authenticate(creds);

        // Generate token
        String jwts = jwtService.getToken(auth.getName());

        // Build response with the generated token
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();

    }
}
