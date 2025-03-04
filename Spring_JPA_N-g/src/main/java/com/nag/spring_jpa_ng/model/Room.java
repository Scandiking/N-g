package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;


@Entity
@Table(name = "room")


public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="room_id")
    private int room_id;

    @Column(name="room_name")
    private String room_name;

    @Column(name="room_descr")
    private String room_descr;

    @Column(name="room_admin")
    private String room_admin;

    @Column(name="room_picture")
    private byte room_picture;


    public Room() {

    }

    public Room(int room_id, String room_name, String room_descr, String room_admin, byte room_picture) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_descr = room_descr;
        this.room_admin = room_admin;
        this.room_picture = room_picture;
    }

    public long getId() {return id;}

    public int getRoom_id() {return room_id;}

    public String getRoom_name() {return room_name;}

    public String getRoom_descr() {return room_descr;}

    public String getRoom_admin() {return room_admin;}

    public byte getRoom_picture() {return room_picture;}

    public void setRoom_id(int room_id) {this.room_id = room_id;}

    public void setRoom_name(String room_name) {this.room_name = room_name;}

    public void setRoom_descr(String room_descr) {this.room_descr = room_descr;}

    public void setRoom_admin(String room_admin) {this.room_admin = room_admin;}

    public void setRoom_picture(byte room_picture) {this.room_picture = room_picture;}


    @Override

    public String toString() {
        return "Room [id=" + id + ", room_id=" + room_id + ", room_name=" + room_name + ", room_descr=" + room_descr
                + ", room_admin=" + room_admin + ", room_picture=" + room_picture + "]";
    }

}





