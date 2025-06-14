package com.nag.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "task_title",nullable = false)
    private String title;

    @Column(name = "task_descr", nullable = false)
    private String description;

    @Column(name = "due_date_time", nullable = true)
    private LocalDateTime dueDate;

    @Column(name = "noti_freq_id", nullable = false, insertable = false, updatable = false)
    private String notiFreqId;

    @Column(name = "creator",nullable = false, insertable = false, updatable = false)
    private String creator;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    /** */
    @ManyToOne
    @JoinColumn(name = "noti_freq_id", referencedColumnName = "noti_freq_id")
    private NotiFreq notiFreq;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "phone_no")
    private Person person;


    // Konstruktører
    public Task() {

    }

    /**

    // Getters og Setters
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
     */
}