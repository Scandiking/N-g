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
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @Column(name = "room_id")
    private Short roomId;

    @Column(name = "room_name", nullable = false, length = 30)
    private String roomName;

    @Column(name = "room_descr", length = 70)
    private String roomDescr;

    @Column(name = "room_admin", nullable = false, length = 15)
    private String roomAdmin;

    // Temporarily commented out to avoid JSON serialization issues
    // @Column(name = "room_picture")
    // private byte[] roomPicture;

    // JPA Relationship: Room belongs to Person (admin) - now properly configured
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_admin", referencedColumnName = "phone_no",
            insertable = false, updatable = false)
    @JsonIgnore // Prevent JSON serialization issues
    private Person admin;

    // JPA Relationship: Room has many Tasks (One-to-Many)
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Prevent JSON serialization issues and performance problems
    private List<Task> tasks;

    // Constructors
    public Room(Short roomId, String roomName, String roomDescr, String roomAdmin, byte[] roomPicture) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDescr = roomDescr;
        this.roomAdmin = roomAdmin;
        this.tasks = new ArrayList<>();
        // this.roomPicture = roomPicture;
    }

    public Room(Short roomId, String roomName, String roomAdmin) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomAdmin = roomAdmin;
        this.tasks = new ArrayList<>();
    }

    // Helper method to get admin Person object - with @JsonIgnore
    @JsonIgnore
    public Person getAdminPerson() {
        return this.admin;
    }

    // Helper methods for Room → Tasks relationship
    @JsonIgnore
    public List<Task> getRoomTasks() {
        return this.tasks;
    }

    @JsonIgnore
    public void addTask(Task task) {
        this.tasks.add(task);
        task.setRoomId(this.roomId);
    }

    @JsonIgnore
    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setRoomId(null);
    }

    @JsonIgnore
    public int getTaskCount() {
        return this.tasks != null ? this.tasks.size() : 0;
    }
}