package com.nag.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitet som representerer koblingen mellom {@link Room} og {@link Person}.
 * Inneholder også en ekstra attributt, score,
 *
 * Tabell: room_for_person
 * Klassen bruker en sammensatt primærnøkkel definert i {@link RoomForPersonId}.
 *
 * @author Mia
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room_for_person")
@IdClass(RoomForPersonId.class)

public class RoomForPerson {

    /**
     *Id til rommet, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name ="room_id")
    private Short roomId;

    /**
     *Id til personen, del av sammensatt primærnøkkel
     */
    @Id
    @Column(name ="person_id")
    private String personId;

    /**
     *Poengsum knyttett til relasjonen mellom rom og person.
     * Husk å spørre hva hensikten med denne er ?
     */
    @Column(name = "score", nullable = false)
    private Short score;

    /**
     *Relasjon til {@link Person} entiteten.
     * <p>
     *     Denne koblingen brukes for å få tilgang til hele personobjektet direkte,
     *     uten å slå det opp manuelt via {@code personId}.
     *     {@code insertable = false, updatable = false} betyr at person_id ikke kan endres herfra.
     *     Det styres av {@code personId} feltet direkte.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    /**
     * RRelasjon til {@link Room} entiteten.
     * <p>
     *     Kobler til rommet som personen er knyttet til.
     *     Verdien styres av {@code roomId} feltet og oppdateres ikke via dette objektet.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;
}
