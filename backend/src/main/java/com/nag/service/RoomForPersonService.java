package com.nag.service;

import com.nag.model.RoomForPerson;
import com.nag.model.RoomForPersonId;
import com.nag.repository.RoomForPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing the association between Room and Person (RoomForPerson).
 * Provides methods for CRUD operations and score updates on the composite entity.
 *
 */

// @author Jonas


@Service
@Transactional
public class RoomForPersonService {

    private final RoomForPersonRepository repository;

    public RoomForPersonService(RoomForPersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all RoomForPerson relations.
     * @return list of all relations
     */
    public List<RoomForPerson> getAll() {
        return repository.findAll();
    }

    /**
     * Find a specific RoomForPerson by composite key.
     * @param roomId the room ID
     * @param personId the person ID
     * @return optional containing the relation if found
     */
    public Optional<RoomForPerson> getById(Short roomId, String personId) {
        RoomForPersonId id = new RoomForPersonId(roomId, personId);
        return repository.findById(id);
    }

    /**
     * Find all relations for a given room.
     * @param roomId the room ID
     * @return list of relations
     */
    public List<RoomForPerson> getByRoomId(Short roomId) {
        return repository.findByRoomId(roomId);
    }

    /**
     * Find all relations for a given person.
     * @param personId the person ID
     * @return list of relations
     */
    public List<RoomForPerson> getByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }

    /**
     * Create a new RoomForPerson relation.
     * @param association the RoomForPerson to save
     * @return the saved entity
     */
    public RoomForPerson create(RoomForPerson association) {
        return repository.save(association);
    }

    /**
     * Update an existing RoomForPerson relation (including score).
     * @param association the updated RoomForPerson
     * @return the saved entity
     */
    public RoomForPerson update(RoomForPerson association) {
        // Ensures entity exists or will throw in save
        return repository.save(association);
    }

    /**
     * Delete a RoomForPerson relation by composite key.
     * @param roomId the room ID
     * @param personId the person ID
     */
    public void delete(Short roomId, String personId) {
        RoomForPersonId id = new RoomForPersonId(roomId, personId);
        repository.deleteById(id);
    }

    /**
     * Update only the score attribute for a given room-person relation.
     * @param roomId the room ID
     * @param personId the person ID
     * @param newScore the new score
     * @return the updated entity
     */
    public RoomForPerson updateScore(Short roomId, String personId, Short newScore) {
        RoomForPersonId id = new RoomForPersonId(roomId, personId);
        RoomForPerson association = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Relation not found for roomId=%d, personId=%s", roomId, personId)
                ));
        association.setScore(newScore);
        return repository.save(association);
    }
}
