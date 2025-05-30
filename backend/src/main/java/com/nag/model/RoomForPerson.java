package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitet som representerer koblingen mellom {@link Room} og {@link Person}.
 * Inneholder også en ekstra attributt, score.
 *
 * Tabell: room_for_person
 * Klassen bruker en sammensatt primærnøkkel definert i {@link RoomForPersonId}.
 *
 * @author Mia & Jonas
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room_for_person")
@IdClass(RoomForPersonId.class)
public class RoomForPerson {

    /**
     * Id til rommet, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name = "room_id")
    private Short roomId;

    /**
     * Telefonnummer til personen, del av sammensatt primærnøkkel.
     */
    @Id
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * Poengsum knyttet til relasjonen mellom rom og person.
     */
    @Column(name = "score", nullable = false)
    private Short score;

    /**
     * Relasjon til {@link Person} entiteten.
     * <p>
     *     Denne koblingen brukes for å få tilgang til hele personobjektet direkte,
     *     uten å slå det opp manuelt via {@code phoneNo}.
     *     {@code insertable = false, updatable = false} betyr at phone_no ikke kan endres herfra.
     *     Det styres av {@code phoneNo} feltet direkte.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "phone_no", insertable = false, updatable = false)
    private Person person;

    /**
     * Relasjon til {@link Room} entiteten.
     * <p>
     *     Kobler til rommet som personen er knyttet til.
     *     Verdien styres av {@code roomId} feltet og oppdateres ikke via dette objektet.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;
}
