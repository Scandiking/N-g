/**
 * Repository interface for managing Room entities.
 * This interface extends JpaRepository to provide CRUD operations for Room entities.
 *
 * @author Kenneth
 */

package com.nag.repository;

import com.nag.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoomRepository extends JpaRepository<Room, Short> {

    /**
     * Finds a room by its room ID.
     *
     * @param roomId the ID of the room
     * @return Optional containing the room if found, otherwise empty
     */
    Optional<Room> findByRoomId(Short roomId);

    /**
     * Finds all rooms by room name.
     *
     * @param roomName the name of the room
     * @return a list of rooms with the specified name
     */
    List<Room> findByRoomName(String roomName);

    /**
     * Finds all rooms containing the specified text in room name.
     *
     * @param roomName the text to search for in room names
     * @return a list of rooms containing the specified text in name
     */
    List<Room> findByRoomNameContaining(String roomName);

    /**
     * Finds all rooms by room admin.
     *
     * @param roomAdmin the phone number of the room admin
     * @return a list of rooms administered by the specified person
     */
    List<Room> findByRoomAdmin(String roomAdmin);

    /**
     * Finds all rooms by room description.
     *
     * @param roomDescr the room description
     * @return a list of rooms with the specified description
     */
    List<Room> findByRoomDescr(String roomDescr);

    /**
     * Finds all rooms containing the specified text in room description.
     *
     * @param roomDescr the text to search for in room descriptions
     * @return a list of rooms containing the specified text in description
     */
    List<Room> findByRoomDescrContaining(String roomDescr);

    /**
     * Checks if a room exists by room ID.
     *
     * @param roomId the ID of the room
     * @return true if the room exists, false otherwise
     */
    boolean existsByRoomId(Short roomId);

    /**
     * Deletes a room by room ID.
     *
     * @param roomId the ID of the room to delete
     */
    void deleteByRoomId(Short roomId);
}