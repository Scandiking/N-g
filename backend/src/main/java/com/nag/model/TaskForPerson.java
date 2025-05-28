package com.nag.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// @author: Jonas

public class TaskForPerson {

    @Id
    @Column(name = "task_id")
    private Integer taskId; // Unique identifier for the task

    @Id
    @Column(name = "phone_no")
    private String phoneNo; // Phone number of the person assigned to the task

    @ManyToOne
    @JoinColumn(name = "phone_no", insertable = false, updatable = false)
    private Person person; // The person entity associated with this assignment

    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task; // The task entity associated with this assignment
}
t