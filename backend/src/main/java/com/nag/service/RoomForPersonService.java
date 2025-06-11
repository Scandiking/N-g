package com.nag.service;

import com.nag.dto.RoomForPersonDTO;
import com.nag.mapper.RoomForPersonMapper;
import com.nag.model.RoomForPerson;
import com.nag.model.RoomForPersonId;
import com.nag.repository.RoomForPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing RoomForPerson relationships.
 * Handles business logic for associating persons with rooms including scores.
 *
 * @author Generated Service
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RoomForPersonService {

    private final RoomForPersonRepository roomForPersonRepository;
    private final RoomForPersonMapper roomForPersonMapper;

    /**
     * Get all room-person relationships.
     *
     * @return list of all RoomForPersonDTO
     */
    public List<RoomForPersonDTO> getAllRoomForPersons() {
        List<RoomForPerson> roomForPersons = roomForPersonRepository.findAll();
        return roomForPersonMapper.toRoomForPersonDTOs(roomForPersons);
    }

    /**
     * Get a specific room-person relationship by composite key.
     *
     * @param roomId the room ID
     * @param phoneNo the person's phone number
     * @return RoomForPersonDTO if found
     * @throws RuntimeException if not found
     */
    public RoomForPersonDTO getRoomForPersonById(Short roomId, String phoneNo) {
        RoomForPersonId id = new RoomForPersonId(roomId, phoneNo);
        RoomForPerson roomForPerson = roomForPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomForPerson not found with roomId: " + roomId + " and phoneNo: " + phoneNo));
        return roomForPersonMapper.toRoomForPersonDTO(roomForPerson);
    }

    /**
     * Get all persons associated with a specific room.
     *
     * @param roomId the room ID
     * @return list of RoomForPersonDTO for the specified room
     */
    public List<RoomForPersonDTO> getPersonsByRoomId(Short roomId) {
        List<RoomForPerson> roomForPersons = roomForPersonRepository.findByRoomId(roomId);
        return roomForPersonMapper.toRoomForPersonDTOs(roomForPersons);
    }

    /**
     * Get all rooms associated with a specific person.
     *
     * @param phoneNo the person's phone number
     * @return list of RoomForPersonDTO for the specified person
     */
    public List<RoomForPersonDTO> getRoomsByPhoneNo(String phoneNo) {
        List<RoomForPerson> roomForPersons = roomForPersonRepository.findByPhoneNo(phoneNo);
        return roomForPersonMapper.toRoomForPersonDTOs(roomForPersons);
    }

    /**
     * Create a new room-person relationship.
     *
     * @param roomForPersonDTO the data to create
     * @return the created RoomForPersonDTO
     */
    public RoomForPersonDTO createRoomForPerson(RoomForPersonDTO roomForPersonDTO) {
        // Check if relationship already exists
        RoomForPersonId id = new RoomForPersonId(roomForPersonDTO.getRoomId(), roomForPersonDTO.getPhoneNo());
        if (roomForPersonRepository.existsById(id)) {
            throw new RuntimeException("RoomForPerson relationship already exists for roomId: " +
                    roomForPersonDTO.getRoomId() + " and phoneNo: " + roomForPersonDTO.getPhoneNo());
        }

        RoomForPerson roomForPerson = roomForPersonMapper.toRoomForPerson(roomForPersonDTO);
        RoomForPerson savedRoomForPerson = roomForPersonRepository.save(roomForPerson);
        return roomForPersonMapper.toRoomForPersonDTO(savedRoomForPerson);
    }

    /**
     * Update an existing room-person relationship.
     *
     * @param roomId the room ID
     * @param phoneNo the person's phone number
     * @param roomForPersonDTO the updated data
     * @return the updated RoomForPersonDTO
     */
    public RoomForPersonDTO updateRoomForPerson(Short roomId, String phoneNo, RoomForPersonDTO roomForPersonDTO) {
        RoomForPersonId id = new RoomForPersonId(roomId, phoneNo);

        RoomForPerson existingRoomForPerson = roomForPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomForPerson not found with roomId: " + roomId + " and phoneNo: " + phoneNo));

        // Update the score (main thing that can be updated in this relationship)
        existingRoomForPerson.setScore(roomForPersonDTO.getScore());

        RoomForPerson updatedRoomForPerson = roomForPersonRepository.save(existingRoomForPerson);
        return roomForPersonMapper.toRoomForPersonDTO(updatedRoomForPerson);
    }

    /**
     * Delete a room-person relationship.
     *
     * @param roomId the room ID
     * @param phoneNo the person's phone number
     */
    public void deleteRoomForPerson(Short roomId, String phoneNo) {
        RoomForPersonId id = new RoomForPersonId(roomId, phoneNo);

        if (!roomForPersonRepository.existsById(id)) {
            throw new RuntimeException("RoomForPerson not found with roomId: " + roomId + " and phoneNo: " + phoneNo);
        }

        roomForPersonRepository.deleteById(id);
    }

    /**
     * Delete all room associations for a specific person.
     *
     * @param phoneNo the person's phone number
     */
    public void deleteAllRoomsForPerson(String phoneNo) {
        roomForPersonRepository.deleteByPhoneNo(phoneNo);
    }

    /**
     * Delete all person associations for a specific room.
     *
     * @param roomId the room ID
     */
    public void deleteAllPersonsForRoom(Short roomId) {
        roomForPersonRepository.deleteByRoomId(roomId);
    }

    /**
     * Check if a room-person relationship exists.
     *
     * @param roomId the room ID
     * @param phoneNo the person's phone number
     * @return true if relationship exists, false otherwise
     */
    public boolean existsRoomForPerson(Short roomId, String phoneNo) {
        RoomForPersonId id = new RoomForPersonId(roomId, phoneNo);
        return roomForPersonRepository.existsById(id);
    }
}