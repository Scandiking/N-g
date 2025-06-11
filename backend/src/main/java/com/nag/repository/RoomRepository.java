package com.nag.repository;

import com.nag.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Room entities.
 * This interface extends JpaRepository to provide CRUD operations for Room entities.
 *
 * @author Kenneth
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Short> {

    /**
     * Finds all rooms by room name.
     * Useful for finding rooms with exact name matches.
     *
     * @param roomName the name of the room
     * @return a list of rooms with the specified name
     */
    List<Room> findByRoomName(String roomName);

    /**
     * Finds all rooms containing the specified text in room name.
     * Useful for search functionality with partial matching.
     *
     * @param roomName the text to search for in room names
     * @return a list of rooms containing the specified text in name
     */
    List<Room> findByRoomNameContaining(String roomName);

    /**
     * Finds all rooms administered by a specific person.
     * Very useful for user-specific room management.
     *
     * @param roomAdmin the phone number of the room admin
     * @return a list of rooms administered by the specified person
     */
    List<Room> findByRoomAdmin(String roomAdmin);

    /**
     * Finds all rooms containing the specified text in room description.
     * Useful for content-based room search.
     *
     * @param roomDescr the text to search for in room descriptions
     * @return a list of rooms containing the specified text in description
     */
    List<Room> findByRoomDescrContaining(String roomDescr);

    // Valgfritt - hvis dere trenger exact description match:
    // List<Room> findByRoomDescr(String roomDescr);
}