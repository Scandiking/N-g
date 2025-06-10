package com.nag.service;

import com.nag.dto.RoomForPersonDTO;
import com.nag.mapper.RoomForPersonMapper;
import com.nag.model.RoomForPerson;
import com.nag.model.RoomForPersonId;
import com.nag.repository.RoomForPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing the association between Room and Person (RoomForPerson).
 * Provides methods for CRUD operations and score updates on the composite entity.
 */

// @author Jonas


@Service
@Transactional
@RequiredArgsConstructor
public class RoomForPersonService {
    private final RoomForPersonRepository roomForPersonRepository;
    private final RoomForPersonMapper roomForPersonMapper;

    /**
     * Retrieve all RoomForPerson relations.
     *
     * @return list of all relations
     */
    public List<RoomForPersonDTO> getAllRoomForPersons() {
        List<RoomForPerson> roomForPersons = roomForPersonRepository.findAll();
        return roomForPersonMapper.toRoomForPersonDTOs(roomForPersons);
    }

    /**
     * Find a specific RoomForPerson by composite key
     *
     * @param roomId the room ID
     * @return optional containing the relation if found
     */
    public RoomForPersonDTO getRoomForPersonById(Short roomId, String phoneNo) {
        RoomForPersonId roomForPersonId = new RoomForPersonId(roomId, phoneNo); // Create composite key
        RoomForPerson roomForPerson = roomForPersonRepository.findById(roomForPersonId)
                .orElseThrow(() -> new EntityNotFoundException("RoomForPerson not found"));
        return roomForPersonMapper.toRoomForPersonDTO(roomForPerson); // Map to DTO
    }

    /**
     * Find all relations for a given room.
     *
     * @param roomId the room ID
     * @return list of relations
     */
    public List<RoomForPerson> getByRoomId(Short roomId) {
        return roomForPersonRepository.findByRoomId(roomId);
    }

    /**
     * Find all relations for a given person.
     *
     * @param phoneNo the person ID
     * @return list of relations
     */
    public List<RoomForPerson> getByPersonId(String phoneNo) {
        return roomForPersonRepository.findByPersonId(phoneNo);
    }

    /**
     * Create a new RoomForPerson relation.
     *
     * @param roomForPersonDTO the RoomForPerson to save
     * @return the saved entity
     */
    public RoomForPersonDTO createRoomForPerson(@Valid RoomForPersonDTO roomForPersonDTO) {
        RoomForPerson roomForPerson = new RoomForPerson();

        roomForPerson.setRoomId(roomForPersonDTO.getRoomId());
        roomForPerson.setPersonId(roomForPersonDTO.getPhoneNo());
        roomForPerson.setScore(roomForPersonDTO.getScore());

        RoomForPerson savedRoomForPerson = roomForPersonRepository.save(roomForPerson);
        return roomForPersonMapper.toRoomForPersonDTO(savedRoomForPerson);
    }

    /**
     * Update an existing RoomForPerson relation (including score).
     *
     * @param 'roomForPerson' the updated RoomForPerson
     * @return the saved entity
     */
    public RoomForPersonDTO updateRoomForPerson(Short roomId, String phoneNo, @Valid RoomForPersonDTO roomForPersonDTO) {
        RoomForPersonId roomForPersonId = new RoomForPersonId(roomId, phoneNo);

        RoomForPerson existingRoomForPerson = roomForPersonRepository.findById(roomForPersonId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("RoomForPerson not found for roomId=%d, personId=%s", roomId, phoneNo)
                ));

        existingRoomForPerson.setScore(roomForPersonDTO.getScore());
        RoomForPerson updatedRoomForPerson = roomForPersonRepository.save(existingRoomForPerson);

        return roomForPersonMapper.toRoomForPersonDTO(updatedRoomForPerson);

    }

    /**
     * Delete a RoomForPerson relation by composite key.
     *
     * @param roomId  the room ID
     * @param phoneNo the person ID
     */
    public void deleteRoomForPerson(Short roomId, String phoneNo) {
        RoomForPersonId roomForPersonId = new RoomForPersonId(roomId, phoneNo);
        roomForPersonRepository.deleteById(roomForPersonId);
    }

    /**
     * Update only the score attribute for a given room-person relation.
     *
     * @param roomId   the room ID
     * @param phoneNo  the person ID
     * @param newScore the new score
     * @return the updated entity
     */
    public RoomForPerson updateScore(Short roomId, String phoneNo, Short newScore) {
        RoomForPersonId roomForPersonId = new RoomForPersonId(roomId, phoneNo);
        RoomForPerson association = roomForPersonRepository.findById(roomForPersonId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Relation not found for roomId=%d, personId=%s", roomId, phoneNo)
                ));
        association.setScore(newScore);
        return roomForPersonRepository.save(association);
    }
}