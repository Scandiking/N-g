package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room_for_person")
@IdClass(RoomForPersonId.class)
public class RoomForPerson {

    @Id
    @Column(name = "room_id")
    private Short roomId;

    @Id
    @Column(name = "phone_no")  // ✅ Fixed from person_id
    private String phoneNo;

    @Column(name = "score", nullable = false)
    private Short score;

    @ManyToOne
    @JoinColumn(name = "phone_no", insertable = false, updatable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;

    public RoomForPerson(Short roomId, String phoneNo, Short score) {
        this.roomId = roomId;
        this.phoneNo = phoneNo;
        this.score = score;
    }
}