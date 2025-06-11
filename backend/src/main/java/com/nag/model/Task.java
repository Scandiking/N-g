// src/main/java/com/nag/model/Task.java
package com.nag.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a task in the system.
 * Tasks are created by persons and have notification frequency settings.
 */
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "task_title", nullable = false)
    private String title;

    @Column(name = "task_descr", nullable = false)
    private String description;

    /**
     * When the task is due.
     */
    @Column(name = "due_date_time")
    private LocalDateTime dueDate;

    /**
     * Notification frequency ID (matches your noti_freq table).
     */
    @Column(name = "noti_freq_id")
    private Short notiFreqId;

    /**
     * The phoneNo of the user who created this task.
     */
    @Column(name = "creator", nullable = false)
    private String creator;

    /**
     * Whether the task is marked completed.
     */
    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    /**
     * Relation to notification frequency for this task.
     */
    @ManyToOne
    @JoinColumn(name = "noti_freq_id", referencedColumnName = "noti_freq_id")
    private NotiFreq notiFreq;

    /**
     * Relation to the person who created this task.
     */
    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "phone_no")
    private Person person;
}
