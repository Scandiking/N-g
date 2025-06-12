package com.nag.controller;

import com.nag.model.AppUser;
import com.nag.model.Person;
import com.nag.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserProfileController {
    private final AppUserRepository appUserRepository;

    public UserProfileController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("api/profile")
    public ResponseEntity<?> getProfile() {
        try {
            // Get current authenticated user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            // Find user in database
            Optional<AppUser> appUserOpt = appUserRepository.findByUsername(username);

            if (appUserOpt.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "User not found");
                return ResponseEntity.notFound().build();
            }

            AppUser appUser = appUserOpt.get();
            Person person = appUser.getPerson();

            // Build response
            Map<String, Object> profile = new HashMap<>();
            profile.put("username", appUser.getUsername());
            profile.put("role", appUser.getRole());

            if (person != null) {
                profile.put("firstName", person.getFirstName());
                profile.put("lastName", person.getLastName());
                profile.put("email", person.getMail());
                profile.put("phoneNo", person.getPhoneNo());
            }

            return ResponseEntity.ok(profile);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to load profile");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
