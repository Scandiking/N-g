package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_for_person")

public class Task_for_person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "person_id")
    private String personId;

    public Task_for_person() {

    }

    public Task_for_person(int taskId, String personId) {

    }

    public int getTaskId() {
        return taskId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Task_for_person [id=" + id + ", taskId=" + taskId + ", personId=" + personId + "]";
    }
}

