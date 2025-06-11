package com.nag.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Komposittnøkkelklasse for NotiFreqForTask-entiteten
 */
@Getter
@Setter
@NoArgsConstructor
public class NotiFreqForTaskId implements Serializable {  //  IKKE @Embeddable

    private Integer taskId;
    private Short notiFreqId;

    public NotiFreqForTaskId(Integer taskId, Short notiFreqId) {
        this.taskId = taskId;
        this.notiFreqId = notiFreqId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotiFreqForTaskId)) return false;
        NotiFreqForTaskId that = (NotiFreqForTaskId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(notiFreqId, that.notiFreqId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, notiFreqId);
    }
}