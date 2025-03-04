package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "person")

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mail")
    private String email;

    @Column(name="profile_pic")
    private Byte profilePic;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    public Person() {

    }

    public Person(String phoneNo, String firstName, String lastName, String email, Byte profilePic, Date dateOfBirth) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePic = profilePic;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Byte getProfilePic() {
        return profilePic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePic(Byte profilePic) {
        this.profilePic = profilePic;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }





    @Override
    public String toString() {
        return "Person [id=" + id + ", phoneNo=" + phoneNo + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", profilePic=" + profilePic + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
