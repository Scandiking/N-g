package com.nag.service;

import com.nag.dto.RoomDTO;
import com.nag.mapper.RoomMapper;
import com.nag.model.Room;
import com.nag.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing rooms.
 * Provides business logic for room operations including CRUD operations.
 *
 * @author Kristian
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    /**
     * Get all rooms.
     *
     * @return List of RoomDTO containing all rooms
     */
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toRoomDTOs(rooms);
    }

    /**
     * Get room by ID.
     *
     * @param roomId the ID of the room to retrieve
     * @return RoomDTO containing the room details
     * @throws EntityNotFoundException if room not found
     */
    public RoomDTO getRoomById(Short roomId) {
        Room room = roomRepository.findById(roomId)  // ✅ Use findById instead of findByRoomId
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + roomId));
        return roomMapper.toRoomDTO(room);  // ✅ Return single DTO, not List
    }

    /**
     * Get rooms by name.
     *
     * @param roomName the name of the room to search for
     * @return List of RoomDTO containing rooms with the specified name
     */
    public List<RoomDTO> getRoomsByName(String roomName) {
        List<Room> rooms = roomRepository.findByRoomName(roomName);
        return roomMapper.toRoomDTOs(rooms);
    }

    /**
     * Get rooms by name containing specified text.
     *
     * @param roomName the text to search for in room names
     * @return List of RoomDTO containing rooms with names containing the specified text
     */
    public List<RoomDTO> getRoomsByNameContaining(String roomName) {
        List<Room> rooms = roomRepository.findByRoomNameContaining(roomName);
        return roomMapper.toRoomDTOs(rooms);
    }

    /**
     * Get all rooms administered by a specific person.
     *
     * @param roomAdmin the phone number of the room admin
     * @return List of RoomDTO for rooms administered by the specified person
     */
    public List<RoomDTO> getRoomsByAdmin(String roomAdmin) {
        List<Room> rooms = roomRepository.findByRoomAdmin(roomAdmin);
        return roomMapper.toRoomDTOs(rooms);
    }

    /**
     * Get rooms by description containing specified text.
     *
     * @param description the text to search for in room descriptions
     * @return List of RoomDTO containing rooms with descriptions containing the specified text
     */
    public List<RoomDTO> getRoomsByDescriptionContaining(String description) {
        List<Room> rooms = roomRepository.findByRoomDescrContaining(description);
        return roomMapper.toRoomDTOs(rooms);
    }

    /**
     * Create a new room.
     *
     * @param roomDTO the room data to create
     * @return RoomDTO containing the created room details
     */
    public RoomDTO createRoom(RoomDTO roomDTO) {
        // ✅ Use mapper instead of manual field setting
        Room room = roomMapper.toRoom(roomDTO);

        Room savedRoom = roomRepository.save(room);
        return roomMapper.toRoomDTO(savedRoom);
    }

    /**
     * Update an existing room.
     *
     * @param roomId the ID of the room to update
     * @param roomDTO the new details for the room
     * @return RoomDTO containing the updated room details
     * @throws EntityNotFoundException if room not found
     */
    public RoomDTO updateRoom(Short roomId, RoomDTO roomDTO) {
        Room existingRoom = roomRepository.findById(roomId)  // ✅ Use findById instead of findByRoomId
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + roomId));

        // Update the fields
        existingRoom.setRoomName(roomDTO.getRoomName());
        existingRoom.setRoomDescr(roomDTO.getRoomDescr());
        existingRoom.setRoomAdmin(roomDTO.getRoomAdmin());
        existingRoom.setRoomPicture(roomDTO.getRoomPicture());

        Room updatedRoom = roomRepository.save(existingRoom);
        return roomMapper.toRoomDTO(updatedRoom);
    }

    /**
     * Delete a room by ID.
     *
     * @param roomId the ID of the room to delete
     * @throws EntityNotFoundException if room not found
     */
    public void deleteRoom(Short roomId) {
        if (!roomRepository.existsById(roomId)) {  // ✅ Use existsById instead of existsByRoomId
            throw new EntityNotFoundException("Room not found with id: " + roomId);
        }
        roomRepository.deleteById(roomId);
    }

    /**
     * Check if a room exists by ID.
     *
     * @param roomId the ID of the room
     * @return true if room exists, false otherwise
     */
    public boolean existsRoom(Short roomId) {
        return roomRepository.existsById(roomId);
    }

    /**
     * Get the total number of rooms.
     *
     * @return the total count of rooms
     */
    public long getRoomCount() {
        return roomRepository.count();
    }
}