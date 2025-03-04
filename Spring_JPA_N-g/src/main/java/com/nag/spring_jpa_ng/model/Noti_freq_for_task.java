package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Noti_freq_for_task")
public class Noti_freq_for_task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="Task_id")
    private String taskId;

    @Column(name="Noti_freq_id")
    private String notiFreqId;

    public Noti_freq_for_task() {
    }

    public Noti_freq_for_task(String taskId, String notiFreqId) {
        this.taskId = taskId;
        this.notiFreqId = notiFreqId;
    }

    public long getId() { return id; }

    public String getTaskId() { return taskId; }

    public String getNotiFreqId() { return notiFreqId; }

    public void setTaskId(String taskId) { this.taskId = taskId; }

    public void setNotiFreqId(String notiFreqId) { this.notiFreqId = notiFreqId; }

    @Override
    public String toString() {
        return "Noti_freq_for_task [id=" + id + ", taskId=" + taskId + ", notiFreqId=" + notiFreqId + "]";
    }
}