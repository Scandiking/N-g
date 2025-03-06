package com.nag.spring_jpa_ng.controller;

import com.nag.spring_jpa_ng.model.Room;
import com.nag.spring_jpa_ng.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4321") // Crossorigin is for configuring allowed origins
@RestController // annotation is used to define a controller and to indicate that the return value of the methods should be bound to the web response body
@RequestMapping("/api") // declares that all API's url in the controller will start with /api
// We use @Autowired to inject N_gRepository bean to local variable

public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    // Get all rooms
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get room by id
    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable(value = "id") Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId + "\n" + "Please check the id and try again."));
    }

    // Get room by name
    @GetMapping("/rooms/{roomName}")
    public List<Room> getRoomByName(@PathVariable(value = "roomName") String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    // Get room by description
    @GetMapping("/rooms/{roomDescr}")
    public List<Room> getRoomByDescr(@PathVariable(value = "roomDescr") String roomDescr) {
        return roomRepository.findByRoomDescr(roomDescr);
    }

    // Get room by admin
    @GetMapping("/rooms/{roomAdmin}")
    public List<Room> getRoomByAdmin(@PathVariable(value = "roomAdmin") String roomAdmin) {
        return roomRepository.findByRoomAdmin(roomAdmin);
    }
}
