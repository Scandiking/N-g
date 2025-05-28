/**
 * @description Entity class representing a person in the application.
 * This class storesinformation for persons such as personId (telephone number), name, email, profileImage, and birthDate.
 */

package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "PERSON")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "person_id")
    private String personId;

    @Column(name = "navn", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Lob // @Lob tells JPA this is a large object (BLOB for binary).
    @Basic(fetch = FetchType.LAZY) // avoids pulling the entire byte array until you actually call getProfileImage().
    @Column(name = "profilbilde", columnDefinition = "bytea")
    private byte[] profileImage; // ensures PostgreSQL uses its bytea type..


    @Column(name = "fødselsdato", nullable = false)
    private LocalDate birthDate;

    public Person(String personId, String name, String email, String profileImage, LocalDate birthDate) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.birthDate = birthDate;
    }

    public Person(String personId, String name, String email) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.profileImage = null;
        this.birthDate = null;
    }
}
