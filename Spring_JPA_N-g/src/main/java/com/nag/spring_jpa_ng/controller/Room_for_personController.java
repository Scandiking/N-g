package com.nag.spring_jpa_ng.controller;

import com.nag.spring_jpa_ng.model.Room_for_person;
import com.nag.spring_jpa_ng.repository.Room_for_personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//  Mia

@RestController
@RequestMapping("/api/room-for-person")

public class Room_for_personController {

    @Autowired
    private Room_for_personRepository roomForPersonRepository;

    //Hente alle Room_for_person
    @GetMapping
    public List<Room_for_person> getAllRoomForPerson() {
        return roomForPersonRepository.findAll();
    }

    //Hente alle Room_for_person basert på roomID
    @GetMapping("/room/{roomId}")
    public List<Room_for_person> getRoomForPersonByRoomId(@PathVariable int roomId) {
        return roomForPersonRepository.findByRoomId(roomId);
    }

    //Hente alle Room_for_person basert på personId
    @GetMapping("/person/{personId}")
    public List<Room_for_person> getRoomForPersonByPersonId(@PathVariable Long personId) {
        return roomForPersonRepository.findByPersonId(personId);
    }

    // Hente Room_for_person basert på roomId og PersonId
    @GetMapping("/room/{roomId}/person/{personId}")
    public List<Room_for_person> getRoomForPersonByRoomIdAndPersonId(@PathVariable int roomId, @PathVariable Long personId) {
        return roomForPersonRepository.findByRoomIdAndPersonId(roomId, personId);
    }

    // Hente Room_for_person basert på roomId, personId og role
    @GetMapping("/room/{roomId}/person/{personId}/role/{role}")
    public List<Room_for_person> getRoomForPersonByRoomIdAndByPersonIdAndByRole(@PathVariable int roomId, @PathVariable Long personId, @PathVariable String role) {
        return roomForPersonRepository.findByRoomIdAndPersonIdAndRole(roomId, personId, role);
    }

    // Hente Room_for_person basert på roomId og role
    @GetMapping("/room/{roomId}/role/{role}")
    public List<Room_for_person> getRoomForPersonByRoomIdAndRole(@PathVariable int roomId, @PathVariable String role) {
        return roomForPersonRepository.findByRoomIdAndRole(roomId, role);
    }

    // Hente Room_for_person basert på personId og role
    @GetMapping("/person/{personId}/role/{role}")
    public List<Room_for_person> getRoomForPersonByPersonIdAndRole(@PathVariable Long personId, @PathVariable String role) {
        return roomForPersonRepository.findByPersonIdAndRole(personId, role);
    }

    // Hente Room_for_person basert på spesifisert rolle
    @GetMapping("/role/{role}")
    public List<Room_for_person> getRoomForPersonByRole(@PathVariable String role) {
        return roomForPersonRepository.findByRole(role);
    }

    //Legge til en ny Room_for_person
    @PostMapping
    public ResponseEntity<Room_for_person> createRoomForPerson(@RequestBody Room_for_person roomForPerson) {
        if (roomForPerson == null) {
            return ResponseEntity.badRequest().build();
        }
        Room_for_person savedRoomForPerson = roomForPersonRepository.save(roomForPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoomForPerson);
    }

    // Oppdatere eksisterende room_for_person
    @PutMapping("/{id}")
    public ResponseEntity<Room_for_person> updateRoomForPerson(@PathVariable long id, @RequestBody Room_for_person roomForPerson) {
        Optional<Room_for_person> existingRoomForPerson = roomForPersonRepository.findById(id);
        if (existingRoomForPerson.isPresent()) {
            roomForPerson.setId(id);
            roomForPersonRepository.save(roomForPerson);
            return ResponseEntity.ok(roomForPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Slette en Room_for_person basert på id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomForPerson(@PathVariable long id) {
        Optional<Room_for_person> roomForPerson = roomForPersonRepository.findById(id);

        if(roomForPerson.isPresent()) {
            roomForPersonRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
