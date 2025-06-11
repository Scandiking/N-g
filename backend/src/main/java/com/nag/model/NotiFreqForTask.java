package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitet som representerer koblingen mellom Task og NotiFreq.
 * Junction tabell for notification frequency settings per task.
 *
 * @author Kenneth
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "noti_freq_for_task")
@IdClass(NotiFreqForTaskId.class)  // ✅ Ikke @EmbeddedId
public class NotiFreqForTask {

    /**
     * Id til tasken, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name = "task_id")
    private Integer taskId;  // ✅ Separate field, ikke id.taskId

    /**
     * Id til notification frequency, del av sammensatt primærnøkkel
     */
    @Id
    @Column(name = "noti_freq_id")
    private Short notiFreqId;  // ✅ Separate field, ikke id.notiFreqId

    /**
     * Relasjon til Task entiteten.
     */
    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    /**
     * Relasjon til NotiFreq entiteten.
     */
    @ManyToOne
    @JoinColumn(name = "noti_freq_id", insertable = false, updatable = false)
    private NotiFreq notiFreq;

    /**
     * Konstruktør med ID-feltene
     */
    public NotiFreqForTask(Integer taskId, Short notiFreqId) {
        this.taskId = taskId;
        this.notiFreqId = notiFreqId;
    }

    /**
     * Konstruktør med entitets-objektene
     */
    public NotiFreqForTask(Task task, NotiFreq notiFreq) {
        this.task = task;
        this.notiFreq = notiFreq;
        if (task != null) {
            this.taskId = task.getTaskId();
        }
        if (notiFreq != null) {
            this.notiFreqId = notiFreq.getNotiFreqId();
        }
    }
}