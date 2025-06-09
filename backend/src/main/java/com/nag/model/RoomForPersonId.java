/**
 * Komposittnøkkelklasse for RoomForPerson-entiteten
 * <p>
 *    Klassen brukes til å representere en primærnøkkel som består av to fel(roomId og personId)
 *    Den må implementere serializable
 *    og overskrive #equals(Object) og #hashCode() for at JPA skal kunne bruke den riktig
 * </p>
 *
 * @author Mia
 */

package com.nag.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
public class RoomForPersonId implements Serializable {

    /**
     * Id for rommet, del av sammensatt primærnøkkel
     */
    private Short roomId;

    /**
     * Id for personen, del av den sammensatte primærnøkkelen
     */
    private String personId;

    /**
     * Full konstruktør for å opprette en RoomForPersonID med begge feltene.
     * @param roomId Id for rommet
     * @param personId Id for personen
     */
    public RoomForPersonId(Short roomId, String personId) {
        this.roomId = roomId;
        this.personId = personId;
    }

    /**
     * Sjekker om dette objektet er likt et annet objekt
     * <p>
     *     Denne metoden overskriver {@link Object#equals(Object)} og brukes for å sammenligne to
     *     {@code RoomForPersonId} objekter.
     *     To objekter regnes som like dersom begge har samme {@code roomId} og {@code personId}.
     * </p>
     * @param o objektet som skal sammenlignes
     * @return {@code true} hvois objektene er like, ellers{@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomForPersonId)) return false;
        RoomForPersonId that = (RoomForPersonId) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(personId, that.personId);
    }

    /**
     * Genererer hash-kode for dette objektet
     * <P>
     *     Denne metoden overskriver {@link Object#hashCode()}.
     *     den returnerer en hash- verdi(tall) som representerer objektet, basert på{@code roomId} og {@code personId}.
     *     Dette brukes når objektet skal lagres i en {@code HashMap} eller {@code hashSet}.
     *
     * </P>
     * @return hash-koden for objektet
     */
    @Override
    public int hashCode() {
        return Objects.hash(roomId, personId);
    }

    /**
     * Merk:
     * <p>
     *     Disse metodene er viktige fordi det brukes en komposittnøkkel(to felt som primærnøkkel)
     *     Både {@link#equals(object)} og {@link #hashCode()} må være korrekt implementert,
     *     ellers klarer ikke JPA hente, oppdatere eller sammenligne rader riktig.
     *     Dette kan føre til duplikater, feil ved lagring og at du ikke finner igjen data.
     * </p>
     */

}
