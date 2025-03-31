package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

// Lagt til "rolle" , endret score til int

@Entity
@Table(name = "Room_for_person")
public class Room_for_person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="room_id")
    private int roomId;

    @Column(name="person_id")
    private Long personId;

    @Column(name="score")
    private int score;

    /* for å bruke reposityry med "rolle"*/
    @Column(name="role")
    private String role;

    public Room_for_person() {
    }

    public Room_for_person(int roomId, Long personId, int score, String role) {
        this.roomId = roomId;
        this.personId = personId;
        this.score = score;
        this.role = role;
    }
/* Gettere*/
    public long getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public Long getPersonId() {
        return personId;
    }

    public int getScore() {
        return score;
    }

    /*Getter for rolle*/
    public String getRole() {
        return role;
    }
/*Settere*/
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /* Setter for rolle*/
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Room_for_person [id=" + id + ", roomId=" + roomId + ", personId=" + personId + ", score=" + score + ", role=" + role + "]";
    }
}