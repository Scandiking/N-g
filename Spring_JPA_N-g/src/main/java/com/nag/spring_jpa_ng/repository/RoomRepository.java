package com.nag.spring_jpa_ng.repository;

import com.nag.spring_jpa_ng.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List <Room> findByRoomAdmin(String roomAdmin);
    List <Room> findByRoomNameContaining(String roomName);
    List <Room> findByRoomName(String roomName);
    List <Room> findByRoomDescr(String roomDescr);
}
