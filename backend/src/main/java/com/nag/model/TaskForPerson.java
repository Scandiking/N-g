package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitet som representerer koblingen mellom Task og Person.
 * Junction tabell for å tildele oppgaver til personer.
 *
 * @author Jonas
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "task_for_person")
@IdClass(TaskForPersonId.class)
public class TaskForPerson {

    /**
     * Id til oppgaven, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name = "task_id")
    private Integer taskId;

    /**
     * Telefonnummer til personen, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * Relasjon til Person entiteten.
     */
    @ManyToOne
    @JoinColumn(name = "phone_no", insertable = false, updatable = false)
    private Person person;

    /**
     * Relasjon til Task entiteten.
     */
    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    /**
     * Konstruktør med ID-feltene
     */
    public TaskForPerson(Integer taskId, String phoneNo) {
        this.taskId = taskId;
        this.phoneNo = phoneNo;
    }

    /**
     * Konstruktør med entitets-objektene
     */
    public TaskForPerson(Task task, Person person) {
        this.task = task;
        this.person = person;
        if (task != null) {
            this.taskId = task.getTaskId();
        }
        if (person != null) {
            this.phoneNo = person.getPhoneNo();
        }
    }
}