/**
 * Person.java
 * Entity class representing a person in the application.
 * @description This class stores information about persons including their relationships to tasks and rooms.
 * @author Kristian
 */

package com.nag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @Column(name = "phone_no", length = 15)
    private String phoneNo;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "mail", nullable = false, length = 50)
    private String mail;

    // Profile picture field - with @JsonIgnore to prevent serialization issues
    @Column(name = "profile_picture")
    @JsonIgnore // Prevent JSON serialization issues with byte[]
    private byte[] profilePicture;

    @Column(name = "date_of_birth")
    private LocalDate birthDate;

    // JPA Relationships

    /**
     * One-to-Many relationship: One person can be assigned to many tasks
     */
    @OneToMany(mappedBy = "assignedPerson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Prevent JSON serialization issues
    private List<Task> assignedTasks;

    /**
     * One-to-Many relationship: One person can be admin of many rooms
     */
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Prevent JSON serialization issues
    private List<Room> administeredRooms;

    // Custom constructors with relationship initialization
    public Person(String phoneNo, String firstName, String lastName, String mail, LocalDate birthDate) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.birthDate = birthDate;
        this.assignedTasks = new ArrayList<>();
        this.administeredRooms = new ArrayList<>();
    }

    // Constructor that PersonService expects (with profilePicture)
    public Person(String phoneNo, String firstName, String lastName, String mail, byte[] profilePicture, LocalDate birthDate) {
        this.phoneNo = phoneNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.profilePicture = profilePicture;
        this.birthDate = birthDate;
        this.assignedTasks = new ArrayList<>();
        this.administeredRooms = new ArrayList<>();
    }

    // Helper methods for Person → Tasks relationship
    @JsonIgnore
    public List<Task> getAssignedTasks() {
        return this.assignedTasks;
    }

    @JsonIgnore
    public void addAssignedTask(Task task) {
        this.assignedTasks.add(task);
        task.setAssignedTo(this.phoneNo);
    }

    @JsonIgnore
    public void removeAssignedTask(Task task) {
        this.assignedTasks.remove(task);
        task.setAssignedTo(null);
    }

    @JsonIgnore
    public int getAssignedTaskCount() {
        return this.assignedTasks != null ? this.assignedTasks.size() : 0;
    }

    // Helper methods for Person → Rooms relationship
    @JsonIgnore
    public List<Room> getAdministeredRooms() {
        return this.administeredRooms;
    }

    @JsonIgnore
    public void addAdministeredRoom(Room room) {
        this.administeredRooms.add(room);
        room.setRoomAdmin(this.phoneNo);
    }

    @JsonIgnore
    public void removeAdministeredRoom(Room room) {
        this.administeredRooms.remove(room);
        room.setRoomAdmin(null);
    }

    @JsonIgnore
    public int getAdministeredRoomCount() {
        return this.administeredRooms != null ? this.administeredRooms.size() : 0;
    }

    // Summary helper methods
    @JsonIgnore
    public boolean isAdmin() {
        return getAdministeredRoomCount() > 0;
    }

    @JsonIgnore
    public boolean hasTasks() {
        return getAssignedTaskCount() > 0;
    }
}