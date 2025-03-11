package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

@Entity
@Table(name = "noti_freq_for_task")
public class Noti_freq_for_task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="task_id")
    private int taskId;

    @Column(name="noti_freq_id")
    private int notiFreqId;

    public Noti_freq_for_task() {
        // default constructor
    }

    public Noti_freq_for_task(int taskId, int notiFreqId) {
        this.taskId = taskId;
        this.notiFreqId = notiFreqId;
    }

    public long getId() {
        return id;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getNotiFreqId() {
        return notiFreqId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setNotiFreqId(int notiFreqId) {
        this.notiFreqId = notiFreqId;
    }

    @Override
    public String toString() {
        return "Noti_freq_for_task [id=" + id + ", taskId=" + taskId + ", notiFreqId=" + notiFreqId + "]";
    }
}