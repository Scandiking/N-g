package com.nag.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Komposittnøkkelklasse for TaskForPerson-entiteten
 *
 * @author Jonas
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskForPersonId implements Serializable {

    private Integer taskId;
    private String phoneNo;

    public TaskForPersonId(Integer taskId, String phoneNo) {
        this.taskId = taskId;
        this.phoneNo = phoneNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskForPersonId)) return false;
        TaskForPersonId that = (TaskForPersonId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(phoneNo, that.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, phoneNo);
    }
}