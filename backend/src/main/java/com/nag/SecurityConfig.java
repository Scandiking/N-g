// src/main/java/com/nag/SecurityConfig.java
package com.nag;

import com.nag.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationFilter authenticationFilter;
    private final AuthEntryPoint exceptionHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          AuthenticationFilter authenticationFilter,
                          AuthEntryPoint exceptionHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
        this.exceptionHandler = exceptionHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1) disable CSRF for JWT-based API
                .csrf(csrf -> csrf.disable())
                // 2) allow CORS (configured below)
                .cors(withDefaults -> {})
                // 3) no session; REST is stateless
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 4) configure URL-based access rules
                .authorizeHttpRequests(auth -> auth
                        // allow login & register without token
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        // allow your front-end to fetch rooms & persons
                        .requestMatchers(HttpMethod.GET, "/api/rooms/**", "/api/persons/**").permitAll()
                        // allow swagger-ui & API docs
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        // everything else needs a valid JWT
                        .anyRequest().authenticated()
                )
                // 5) hook in your JWT filter before username/password filter
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 6) custom entry point for auth errors
                .exceptionHandling(e -> e.authenticationEntryPoint(exceptionHandler));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(Arrays.asList("*"));
        cfg.setAllowedMethods(Arrays.asList("*"));
        cfg.setAllowedHeaders(Arrays.asList("*"));
        cfg.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }
}
