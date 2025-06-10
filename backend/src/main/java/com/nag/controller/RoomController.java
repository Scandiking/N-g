/**
 * RoomController.java
 * Controller for managing room-related operations.
 */

package com.nag.controller;

import com.nag.model.Room;
import com.nag.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Retrieves all rooms.
     *
     * @return a list of all rooms
     */
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * Retrieves a room by its ID.
     *
     * @param id the ID of the room
     * @return the room with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Short id) {
        return roomRepository.findById(id)
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new room.
     *
     * @param room the room to create
     * @return the created room with status 201
     */
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room savedRoom = roomRepository.save(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    /**
     * Updates an existing room.
     *
     * @param id the ID of the room to update
     * @param room the updated room data
     * @return the updated room with status 200, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Short id, @RequestBody Room room) {
        if (roomRepository.existsById(id)) {
            room.setRoomId(id);
            Room updatedRoom = roomRepository.save(room);
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a room by its ID.
     *
     * @param id the ID of the room to delete
     * @return 204 No Content if successful, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Short id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Checks if a room exists by its ID.
     *
     * @param id the ID of the room
     * @return true if the room exists, false otherwise
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> roomExists(@PathVariable Short id) {
        boolean exists = roomRepository.existsById(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}