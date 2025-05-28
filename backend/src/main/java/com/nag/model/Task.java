/**
 * @Description
 * @author: Lucas
 */

package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "task")
public class Task {

    @Id // task_id as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private int taskId; // SQL integer

    @Column(name = "task_title", nullable = false)
    private String taskTitle; // SQL varchar

    @Column(name = "task_descr")
    private String taskDescr; // SQL text

    @Column(name = "due_date_time",nullable = false)
    private LocalDateTime dueDateTime; // SQL timestamp

    @Column(name = "noti_freq_id", nullable = false)
    private short notiFreqId; // SQL smallint

    @column(name = "creator", nullable = false)
    private String creator; // SQL varchar

    @Column(name = "completed", nullable = false)
    private boolean completed; // SQL boolean

    // Declaring relationship to NotiFreq table on column noti_freq_id
    @ManyToOne
    @JoinColumn(name="noti_freq_id", referencedColumnName = "noti_freq_id")
    private NotiFreq notiFreq;

    // Declaring relationship to Person table on column phone_no
    @ManyToOne
    @JoinColumn(name="creator", referencedColumnName = "phone_no")
    private Person person;

    // Defult constructor
    public Task() {

    }

    // Args constructor
    public Task(int taskId, String taskTitle, String taskDescr, LocalDateTime dueDateTime, short notiFreqId, String creator, boolean completed){
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescr = taskDescr;
        this.dueDateTime = dueDateTime;
        this.notiFreqId = notiFreqId;
        this.creator = creator;
        this.completed = completed;
    }

    // Tostring method
    @Override
    public String toString() {
        return "Task [id=" + taskId + ", Task tite=" + taskTitle + ", Task description=" + taskDescr + ", Due date=" + dueDateTime + ", notiFreqId=" + notiFreqId + ", Creator=" + creator + ", Completed=" + completed];
    }
}