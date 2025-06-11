package com.nag.controller;

import com.nag.model.AccountCredentials;
import com.nag.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hinkula (Kristian)
 * @description Login controller that returns JWT token in JSON response
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
        try {
            UsernamePasswordAuthenticationToken creds =
                    new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
            Authentication auth = authenticationManager.authenticate(creds);

            // Generate token
            String jwts = jwtService.getToken(auth.getName());

            // Create JSON response with token
            Map<String, String> response = new HashMap<>();
            response.put("token", jwts);
            response.put("username", auth.getName());
            response.put("message", "Login successful");

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // Return error for invalid credentials
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}