package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Room_for_person")
public class Room_for_person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="room_id")
    private String roomId;

    @Column(name="person_id")
    private String personId;

    @Column(name="score")
    private String score;

    public Room_for_person() {
    }

    public Room_for_person(String roomId, String personId, String score) {
        this.roomId = roomId;
        this.personId = personId;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getPersonId() {
        return personId;
    }

    public String getScore() {
        return score;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Room_for_person [id=" + id + ", roomId=" + roomId + ", personId=" + personId + ", score=" + score + "]";
    }
}