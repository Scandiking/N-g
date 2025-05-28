package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "phone_no", length = 15)
    private String phoneNo;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    // maps to the "mail" column in the DB
    @Column(name = "mail", length = 50)
    private String email;

    @Lob // @Lob tells JPA this is a large object (BLOB for binary).
    @Basic(fetch = FetchType.LAZY) // avoids pulling the entire byte array until you actually call getProfilePicture().
    @Column(name = "profile_picture", columnDefinition = "bytea")
    private byte[] profilePicture; // ensures PostgreSQL uses its bytea type...

    @Column(name = "date_of_birth")
    private LocalDate birthDate;

    public Person(String phoneNo, String firstName, String lastName, String email, byte[] profilePicture, LocalDate birthDate)
    {
        this.phoneNo       = phoneNo;
        this.firstName     = firstName;
        this.lastName      = lastName;
        this.email         = email;
        this.profilePicture = profilePicture;
        this.birthDate     = birthDate;
    }

    public Person(String phoneNo, String firstName, String lastName, String email)
    {
        this(phoneNo, firstName, lastName, email, null, null);
    }
}
