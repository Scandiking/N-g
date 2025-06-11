package com.nag.repository;

import com.nag.model.RoomForPerson;
import com.nag.model.RoomForPersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for RoomForPerson-entiteten
 * <p>
 * Gir tilgang til databaseoperasjoner (CRUD) for RoomForPerson-objekter.
 * Bruker sammensatt nøkkel (roomId og phoneNo) for entitets-identifikasjon.
 * </p>
 *
 * @author Mia
 */
@Repository
public interface RoomForPersonRepository extends JpaRepository<RoomForPerson, RoomForPersonId> {

    /**
     * Henter alle personer knyttet til et spesifikt rom.
     * @param roomId ID for rommet
     * @return liste av RoomForPerson-relasjoner for det spesifiserte rommet
     */
    List<RoomForPerson> findByRoomId(Short roomId);

    /**
     * Henter alle rom som en person er knyttet til.
     * @param phoneNo telefonnummer for personen
     * @return liste av RoomForPerson-relasjoner for den spesifiserte personen
     */
    List<RoomForPerson> findByPhoneNo(String phoneNo);

    /**
     * Sletter alle rom-tilknytninger for en spesifikk person.
     * @param phoneNo telefonnummer for personen
     */
    void deleteByPhoneNo(String phoneNo);

    /**
     * Sletter alle person-tilknytninger for et spesifikt rom.
     * @param roomId ID for rommet
     */
    void deleteByRoomId(Short roomId);
}