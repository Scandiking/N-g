package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

// Endret variabel navn til camelCase

@Entity
@Table(name = "room")


public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="room_id")
    private int roomId;

    @Column(name="room_name")
    private String roomName;

    @Column(name="room_descr")
    private String roomDescr;

    @Column(name="room_admin")
    private String roomAdmin;


    @Column(name="room_picture")
    private byte [] roomPicture;


    public Room() {

    }

    public Room(int roomId, String roomName, String roomDescr, String roomAdmin, byte [] roomPicture) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDescr = roomDescr;
        this.roomAdmin = roomAdmin;
        this.roomPicture = roomPicture;
    }

    public long getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDescr() {
        return roomDescr;
    }

    public String getRoomAdmin() {
        return roomAdmin;
    }

    public byte getRoomPicture() {
        return roomPicture;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomDescr(String roomDescr) {
        this.roomDescr = roomDescr;
    }

    public void setRoomAdmin(String roomAdmin) {
        this.roomAdmin = roomAdmin;
    }

    public void setRoomPicture(byte [] roomPicture) {
        this.roomPicture = roomPicture;
    }


    @Override

    public String toString() {
        return "Room [id=" + id + ", roomId=" + roomId + ", roomName=" + roomName + ", roomDescr=" + roomDescr
                + ", roomAdmin=" + roomAdmin + ", roomPicture=" + roomPicture + "]";
    }

}





