package com.nag.spring_jpa_ng.repository;
import java.util.List;
import com.nag.spring_jpa_ng.model.Room_for_person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Room_for_personRepository extends JpaRepository<Room_for_person, Long> {
    // Hente alle room_for_person basert på roomID
    List <Room_for_person> findByRoomId(int roomId);

    //Hente alle Room_for_person basert på persinID
    List <Room_for_person> findByPersonId(Long personId);

    // Hente alle Room_for_person basert på både roomID og personID
    List <Room_for_person> findByRoomIdAndPersonId(int roomId, Long personId);

    // Hente alle Room_for_person basert på roomID, personID og rolle
    List <Room_for_person> findByRoomIdAndPersonIdAndRole(int roomId, Long personId, String role);

    // Hente alle Room_for_person basert på roomID og rolle
    List <Room_for_person> findByRoomIdAndRole(int roomId, String role);

    //Hente alle Room_for_person basert på personID og rolle
    List <Room_for_person> findByPersonIdAndRole(Long personId, String role);

    // Hente alle Room_for_person med spesifisert rolle
    List <Room_for_person> findByRole(String role);
}
