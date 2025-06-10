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
    private String mail;

    // ✅ FJERNET @Lob og @Basic - behold kun @Column
    @Column(name = "profile_picture", columnDefinition = "bytea")
    private byte[] profilePicture; // ensures PostgreSQL uses its bytea type...

    @Column(name = "date_of_birth")
    private LocalDate birthDate;

    public Person(String phoneNo, String firstName, String lastName, String mail, byte[] profilePicture, LocalDate birthDate)
    {
        this.phoneNo       = phoneNo;
        this.firstName     = firstName;
        this.lastName      = lastName;
        this.mail         = mail;
        this.profilePicture = profilePicture;
        this.birthDate     = birthDate;
    }

    public Person(String phoneNo, String firstName, String lastName, String mail)

    {
        this(phoneNo, firstName, lastName, mail, null, null);
    }
}