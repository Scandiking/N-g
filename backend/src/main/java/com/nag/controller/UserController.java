package com.nag.controller;

import com.nag.model.AppUser;
import com.nag.repository.AppUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Operations related to user information")
@RequiredArgsConstructor
public class UserController {

    private final AppUserRepository appUserRepository;

    @GetMapping("/me")
    @Operation(summary = "Get current user information", description = "Retrieve information about the currently authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user information"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, Object>> getCurrentUser(Authentication auth) {
        if (auth == null || auth.getName() == null) {
            return ResponseEntity.status(401).build();
        }

        AppUser user = appUserRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());

        // Include person information if available
        if (user.getPerson() != null) {
            Map<String, Object> personInfo = new HashMap<>();
            personInfo.put("firstName", user.getPerson().getFirstName());
            personInfo.put("lastName", user.getPerson().getLastName());
            personInfo.put("phoneNo", user.getPerson().getPhoneNo());
            userInfo.put("person", personInfo);

            // Use person's name as display info
            String firstName = user.getPerson().getFirstName();
            String lastName = user.getPerson().getLastName();
            if (firstName != null || lastName != null) {
                userInfo.put("displayName", (firstName != null ? firstName : "") +
                        (lastName != null ? " " + lastName : "").trim());
            }
        }

        // For email, we'll use username (which is often the email in authentication systems)
        userInfo.put("email", user.getUsername());

        return ResponseEntity.ok(userInfo);
    }
}