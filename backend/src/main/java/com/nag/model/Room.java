/**
 * @description Entity class representing a room in the application.
 * This class stores information about rooms where tasks are assigned and managed.
 * It includes fields for room ID, name, description, admin, and picture.
 * @author Kenneth
 */

package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @Column(name = "room_id")
    private Short roomId; // korresponderer med SMALLINT i SQL

    @Column(name = "room_name", nullable = false, length = 30)
    private String roomName; // korresponderer med VARCHAR(30) i SQL

    @Column(name = "room_descr", length = 70)
    private String roomDescr; // korresponderer med VARCHAR(70) i SQL

    @Column(name = "room_admin", nullable = false, length = 15)
    private String roomAdmin; // korresponderer med VARCHAR(15) i SQL

    @Column(name = "room_picture")
    private byte[] roomPicture; // korresponderer med BYTEA i SQL

    // Relationships kan legges til her når Person entiteten er klar
    // @ManyToOne
    // @JoinColumn(name = "room_admin", insertable = false, updatable = false)
    // private Person admin;


    public Room(Short roomId, String roomName, String roomDescr, String roomAdmin, byte[] roomPicture) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDescr = roomDescr;
        this.roomAdmin = roomAdmin;
        this.roomPicture = roomPicture;
    }


    public Room(Short roomId, String roomName, String roomAdmin) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomAdmin = roomAdmin;
    }
}