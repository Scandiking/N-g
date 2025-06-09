/**
 * Komposittnøkkelklasse for NotiFreqForTask-entiteten
 * <p>
 *    Klassen brukes til å representere en primærnøkkel som består av to felt (taskId og notiFreqId)
 *    Den må implementere Serializable
 *    og overskrive #equals(Object) og #hashCode() for at JPA skal kunne bruke den riktig
 * </p>
 *
 * @author Kenneth
 */

package com.nag.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Embeddable // Kristian
public class NotiFreqForTaskId implements Serializable {

    /**
     * Id for tasken, del av sammensatt primærnøkkel
     */
    private Integer taskId;

    /**
     * Id for notification frequency, del av den sammensatte primærnøkkelen
     */
    private Short notiFreqId;

    /**
     * Full konstruktør for å opprette en NotiFreqForTaskId med begge feltene.
     * @param taskId Id for tasken
     * @param notiFreqId Id for notification frequency
     */
    public NotiFreqForTaskId(Integer taskId, Short notiFreqId) {
        this.taskId = taskId;
        this.notiFreqId = notiFreqId;
    }

    /**
     * Sjekker om dette objektet er likt et annet objekt
     * <p>
     *     Denne metoden overskriver {@link Object#equals(Object)} og brukes for å sammenligne to
     *     {@code NotiFreqForTaskId} objekter.
     *     To objekter regnes som like dersom begge har samme {@code taskId} og {@code notiFreqId}.
     * </p>
     * @param o objektet som skal sammenlignes
     * @return {@code true} hvis objektene er like, ellers {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotiFreqForTaskId)) return false;
        NotiFreqForTaskId that = (NotiFreqForTaskId) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(notiFreqId, that.notiFreqId);
    }

    /**
     * Genererer hash-kode for dette objektet
     * <P>
     *     Denne metoden overskriver {@link Object#hashCode()}.
     *     den returnerer en hash-verdi(tall) som representerer objektet, basert på {@code taskId} og {@code notiFreqId}.
     *     Dette brukes når objektet skal lagres i en {@code HashMap} eller {@code HashSet}.
     * </P>
     * @return hash-koden for objektet
     */
    @Override
    public int hashCode() {
        return Objects.hash(taskId, notiFreqId);
    }

    /**
     * Merk:
     * <p>
     *     Disse metodene er viktige fordi det brukes en komposittnøkkel(to felt som primærnøkkel)
     *     Både {@link #equals(Object)} og {@link #hashCode()} må være korrekt implementert,
     *     ellers klarer ikke JPA hente, oppdatere eller sammenligne rader riktig.
     *     Dette kan føre til duplikater, feil ved lagring og at du ikke finner igjen data.
     * </p>
     */
}