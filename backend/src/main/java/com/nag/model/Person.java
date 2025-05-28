package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class representing a person in the application.
 * This class stores information about persons who can be assigned tasks and belong to rooms.
 *
 * @author Kenneth
 */
@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "phone_no", length = 15)
    private String phoneNo; // PRIMARY KEY som String (telefonnummer)

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "mail", length = 50)
    private String email;

    @Column(name = "profile_picture")
    private byte[] profilePicture; // korresponderer med BYTEA i SQL

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * Constructor with all required fields
     */
    public Person(String phoneNo, String firstName, String lastName, String email, byte[] profilePicture, LocalDate dateOfBirth) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Constructor with required fields only
     */
    public Person(String phoneNo, String firstName, String lastName) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Hibernate trenger en setId metode selv om vi bruker phoneNo som ID
    public void setId(Long id) {
        // Denne metoden trengs for compatibility med noen Spring operasjoner
        // men vi bruker faktisk phoneNo som ID
    }

    public String getId() {
        return phoneNo; // returnerer phoneNo som ID
    }
}