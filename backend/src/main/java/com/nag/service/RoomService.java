/**
 * @description Service class for managing rooms.
 * @author Kristian
 */

package com.nag.service;



import com.nag.dto.RoomDTO;
import com.nag.mapper.RoomMapper;
import com.nag.model.Room;
import com.nag.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    /**
     * Get all rooms.
     * @return List of RoomDTO containing all rooms.
     */
    // Get all rooms
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toRoomDTOs(rooms);
    }

    /**
     * Get room by ID.
     * @param roomId the ID of the room to retrieve.
     * @return RoomDTO containing the room details.
     */
    // Get room by ID
    public List<RoomDTO> getRoomById(Short roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        return Collections.singletonList(roomMapper.toRoomDTO(room));
    }

    /**
     * Get rooms by name.
     * @param 'roomName' the name of the room to search for.
     * @return List of RoomDTO containing rooms with the specified name.
     */
    // Create a new room
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomName(roomDTO.getRoomName());
        room.setRoomDescr(roomDTO.getRoomDescr());
        room.setRoomAdmin(roomDTO.getRoomAdmin());

        Room savedRoom = roomRepository.save(room);
        return roomMapper.toRoomDTO(savedRoom);
    }

    /**
     * Update an existing room.
     * @param roomId the ID of the room to update.
     * @param roomDTO the new details for the room.
     * @return RoomDTO containing the updated room details.
     */
    // Update an existing room
    public RoomDTO updateRoom(Short roomId, RoomDTO roomDTO) {
        Room room = roomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        room.setRoomName(roomDTO.getRoomName());
        room.setRoomDescr(roomDTO.getRoomDescr());
        room.setRoomAdmin(roomDTO.getRoomAdmin());
        Room updatedRoom = roomRepository.save(room);
        return roomMapper.toRoomDTO(updatedRoom);
    }

    /**
     * Delete a room by ID.
     * @param roomId the ID of the room to delete.
     */
    // Delete a room
    public void deleteRoom(Short roomId) {
        if (!roomRepository.existsByRoomId(roomId)) {
            throw new EntityNotFoundException("Room not found");
        }
        roomRepository.deleteById(roomId);
    }

    // Add custom service methods here

}