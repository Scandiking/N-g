/**
 * Entitet som representerer koblingen mellom {@link Task} og {@link NotiFreq}.
 * Dette er en junction tabell som kobler tasks til deres notification frequency innstillinger.
 *
 * Tabell: noti_freq_for_task
 * Klassen bruker en sammensatt primærnøkkel definert i {@link NotiFreqForTaskId}.
 *
 * @author Kenneth
 */

package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "noti_freq_for_task")
@IdClass(NotiFreqForTaskId.class)
public class NotiFreqForTask {

    /**
     * Id til tasken, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name = "task_id")
    private Integer taskId;

    /**
     * Id til notification frequency, del av sammensatt primærnøkkel
     */
    @Id
    @Column(name = "noti_freq_id")
    private Short notiFreqId;

    /**
     * Relasjon til {@link Task} entiteten.
     * <p>
     *     Denne koblingen brukes for å få tilgang til hele task-objektet direkte,
     *     uten å slå det opp manuelt via {@code taskId}.
     *     {@code insertable = false, updatable = false} betyr at task_id ikke kan endres herfra.
     *     Det styres av {@code taskId} feltet direkte.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    /**
     * Relasjon til {@link NotiFreq} entiteten.
     * <p>
     *     Kobler til notification frequency som brukes for denne tasken.
     *     Verdien styres av {@code notiFreqId} feltet og oppdateres ikke via dette objektet.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "noti_freq_id", insertable = false, updatable = false)
    private NotiFreq notiFreq;

    /**
     * Konstruktør med ID-feltene
     * @param taskId Id for tasken
     * @param notiFreqId Id for notification frequency
     */
    public NotiFreqForTask(Integer taskId, Short notiFreqId) {
        this.taskId = taskId;
        this.notiFreqId = notiFreqId;
    }

    /**
     * Konstruktør med entitets-objektene
     * @param task Task objektet
     * @param notiFreq NotiFreq objektet
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