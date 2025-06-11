package com.nag.model;

// Følger Fullstack development with Spring Boot 3 and React læreboken som sier å lage en AppUser entitet

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // ChatGPT:
    @OneToOne
    @JoinColumn(name = "phone_no", referencedColumnName = "phone_no")
    private Person person;

    //

    public AppUser() {
    }

    public AppUser(String username, String password, String role, Person person) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String r) {
        this.role = r;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person p) {
        this.person = p;
    }

}
