/**
 * Data Transfer Object (DTO) for Task.
 *
 * @description This class is used to transfer task data between different layers of the application.
 * @author Kristian
 */
package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor // Lombok will generate a no-args constructor
@AllArgsConstructor // Lombok will generate a constructor with all fields
public class PersonDTO {
    private String phoneNo;
    private String firstName;
    private String lastName;
    private String mail;
    private byte[] profilePicture; // This is a byte array for the profile picture
    private LocalDate birthDate;

    // No need for setters or getters as Lombok's @Data annotation generates them automatically
}
