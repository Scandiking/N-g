package com.nag.repository;

import com.nag.model.RoomForPerson;
import com.nag.model.RoomForPersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for RoomForPerson-entiteten
 * <p>
 *     Gir tilgang til databaseoperasjoner (CRUD) for RoomForPerson-objekter.
 * </p>
 *
 * @author Mia
 */
@Repository
public interface RoomForPersonRepository extends JpaRepository<RoomForPerson, RoomForPersonId> {

    // Henter alle personer knyttet til et rom
    List<RoomForPerson> findByRoomId(Short roomId);

    //Henter alle rom som en person er knyttet til
    List<RoomForPerson> findByPersonId(String personId);
}