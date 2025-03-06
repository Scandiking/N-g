package com.nag.spring_jpa_ng.model;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "task")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_descr")
    private String taskDescr;

    @Column(name = "due_date_time")
    private Timestamp dueDateTime;

    @Column(name = "noti_freq_id")
    private int notiFreqId;

    @Column(name = "creator")
    private String creator;

    @Column(name = "completed")
    private boolean completed;

    public Task() {

    }

    public Task(int taskId, String taskTitle, String taskDescr, Timestamp dueDateTime, int notiFreqId, String creator, boolean completed) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescr = taskDescr;
        this.dueDateTime = dueDateTime;
        this.notiFreqId = notiFreqId;
        this.creator = creator;
        this.completed = completed;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescr() {
        return taskDescr;
    }

    public Timestamp getDueDateTime() {
        return dueDateTime;
    }

    public int getNotiFreqId() {
        return notiFreqId;
    }

    public String getCreator() {
        return creator;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDescr(String taskDescr) {
        this.taskDescr = taskDescr;
    }

    public void setDueDateTime(Timestamp dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public void setNotiFreqId(int notiFreqId) {
        this.notiFreqId = notiFreqId;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", taskId=" + taskId + ", taskTitle=" + taskTitle + ", taskDescr=" + taskDescr + ", dueDateTime=" + dueDateTime + ", notiFreqId=" + notiFreqId + ", creator=" + creator + ", completed=" + completed + "]";
    }
}