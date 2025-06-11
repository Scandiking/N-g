package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity representing a person in the system.
 * Primary key is phoneNo (String).
 * Contains personal information and profile data.
 *
 * @author Team
 */
@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    /**
     * Primary key - phone number (unique identifier)
     */
    @Id
    @Column(name = "phone_no", length = 15, nullable = false)
    private String phoneNo;

    /**
     * Person's first name
     */
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    /**
     * Person's last name
     */
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    /**
     * Person's email address
     * Maps to "mail" column in database
     */
    @Column(name = "mail", length = 50)
    private String mail;

    /**
     * Profile picture stored as byte array
     * Uses PostgreSQL bytea type
     */
    @Column(name = "profile_picture", columnDefinition = "bytea")
    private byte[] profilePicture;

    /**
     * Person's date of birth
     */
    @Column(name = "date_of_birth")
    private LocalDate birthDate;

    /**
     * Full constructor
     *
     * @param phoneNo phone number (primary key)
     * @param firstName first name
     * @param lastName last name
     * @param mail email address
     * @param profilePicture profile picture as byte array
     * @param birthDate date of birth
     */
    public Person(String phoneNo, String firstName, String lastName,
                  String mail, byte[] profilePicture, LocalDate birthDate) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.profilePicture = profilePicture;
        this.birthDate = birthDate;
    }

    /**
     * Constructor without profile picture and birth date
     *
     * @param phoneNo phone number (primary key)
     * @param firstName first name
     * @param lastName last name
     * @param mail email address
     */
    public Person(String phoneNo, String firstName, String lastName, String mail) {
        this(phoneNo, firstName, lastName, mail, null, null);
    }

    /**
     * Constructor for basic person info (no email)
     *
     * @param phoneNo phone number (primary key)
     * @param firstName first name
     * @param lastName last name
     */
    public Person(String phoneNo, String firstName, String lastName) {
        this(phoneNo, firstName, lastName, null, null, null);
    }

    /**
     * Get full name (first + last name)
     *
     * @return full name as single string
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Check if person has email
     *
     * @return true if mail field is not null and not empty
     */
    public boolean hasMail() {
        return mail != null && !mail.trim().isEmpty();
    }

    /**
     * Check if person has profile picture
     *
     * @return true if profile picture is not null
     */
    public boolean hasProfilePicture() {
        return profilePicture != null && profilePicture.length > 0;
    }

    /**
     * Check if person has birth date
     *
     * @return true if birth date is not null
     */
    public boolean hasBirthDate() {
        return birthDate != null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "phoneNo='" + phoneNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", birthDate=" + birthDate +
                ", hasProfilePicture=" + hasProfilePicture() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return phoneNo != null ? phoneNo.equals(person.phoneNo) : person.phoneNo == null;
    }

    @Override
    public int hashCode() {
        return phoneNo != null ? phoneNo.hashCode() : 0;
    }
}