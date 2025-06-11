package com.nag.controller;

import com.nag.model.AppUser;
import com.nag.model.Person;
import com.nag.repository.AppUserRepository;
import com.nag.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for handling user registration.
 * Creates new Person and AppUser accounts.
 *
 * @author Team
 */
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final PersonRepository personRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Register a new user account.
     * Creates both Person and AppUser entities.
     *
     * @param request registration data
     * @return success/error response
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            // Validate required fields
            if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
                return createErrorResponse("First name is required");
            }
            if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
                return createErrorResponse("Last name is required");
            }
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return createErrorResponse("Email is required");
            }
            if (request.getPhoneNo() == null || request.getPhoneNo().trim().isEmpty()) {
                return createErrorResponse("Phone number is required");
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return createErrorResponse("Password is required");
            }

            // Check if phone number already exists (Person primary key)
            if (personRepository.existsById(request.getPhoneNo())) {
                return createErrorResponse("Phone number already registered");
            }

            // Check if email already exists
            Optional<Person> existingPersonByMail = personRepository.findByMail(request.getEmail());
            if (existingPersonByMail.isPresent()) {
                return createErrorResponse("Email already registered");
            }

            // Check if username already exists (using email as username)
            if (appUserRepository.findByUsername(request.getEmail()).isPresent()) {
                return createErrorResponse("Email already registered");
            }

            // Create Person entity
            Person person = new Person();
            person.setPhoneNo(request.getPhoneNo());
            person.setFirstName(request.getFirstName().trim());
            person.setLastName(request.getLastName().trim());
            person.setMail(request.getEmail().trim().toLowerCase());

            // Save Person first
            Person savedPerson = personRepository.save(person);

            // Create AppUser entity
            AppUser appUser = new AppUser();
            appUser.setUsername(request.getEmail().trim().toLowerCase()); // Use email as username
            appUser.setPassword(passwordEncoder.encode(request.getPassword()));
            appUser.setRole("USER"); // Default role
            appUser.setPerson(savedPerson); // Link to Person

            // Save AppUser
            appUserRepository.save(appUser);

            // Return success response
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("username", appUser.getUsername());
            response.put("name", savedPerson.getFirstName() + " " + savedPerson.getLastName());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Log error and return generic error message
            System.err.println("Registration error: " + e.getMessage());
            e.printStackTrace();
            return createErrorResponse("Registration failed. Please try again.");
        }
    }

    /**
     * Helper method to create error response
     */
    private ResponseEntity<?> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * DTO for registration request data
     */
    public static class RegisterRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNo;
        private String password;

        public RegisterRequest() {}

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}