package com.nag.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;

    // Foreign key fields for relationships
    @Column(name = "room_id")
    private Short roomId;

    @Column(name = "assigned_to", length = 15)
    private String assignedTo; // Phone number of the person

    // JPA Relationships

    /**
     * Many-to-One relationship: Many tasks can belong to the same room
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    @JsonIgnore // Prevent JSON serialization issues
    private Room room;

    /**
     * Many-to-One relationship: Many tasks can be assigned to the same person
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to", referencedColumnName = "phone_no",
            insertable = false, updatable = false)
    @JsonIgnore // Prevent JSON serialization issues
    private Person assignedPerson;

    // Constructors
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Task(String title, String description, String status, Short roomId, String assignedTo) {
        this();
        this.title = title;
        this.description = description;
        this.status = status;
        this.roomId = roomId;
        this.assignedTo = assignedTo;
    }

    // Getters and Setters
    public Integer getTaskId() { return taskId; }
    public void setTaskId(Integer taskId) { this.taskId = taskId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    // Foreign key getters/setters
    public Short getRoomId() { return roomId; }
    public void setRoomId(Short roomId) { this.roomId = roomId; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    // Relationship helper methods - @JsonIgnore needed to prevent serialization issues
    @JsonIgnore
    public Room getTaskRoom() {
        return this.room;
    }

    @JsonIgnore
    public Person getAssignedToPerson() {
        return this.assignedPerson;
    }

    // Helper method to update timestamp on changes
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}