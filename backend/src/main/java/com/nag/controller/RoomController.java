// src/main/java/com/nag/controller/RoomController.java
package com.nag.controller;

import com.nag.dto.RoomDTO;
import com.nag.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Room Management", description = "Operations related to managing rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    @Operation(summary = "Get all rooms")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of rooms"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get room by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved room"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Short id) {
        RoomDTO dto = roomService.getRoomById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new room")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created room"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomDTO> createRoom(@Valid @RequestBody RoomDTO dto) {
        RoomDTO created = roomService.createRoom(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing room")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated room"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomDTO> updateRoom(
            @PathVariable Short id,
            @Valid @RequestBody RoomDTO dto
    ) {
        RoomDTO updated = roomService.updateRoom(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a room")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted room"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteRoom(@PathVariable Short id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if room exists")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Existence check complete"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Boolean> existsRoom(@PathVariable Short id) {
        return ResponseEntity.ok(roomService.existsRoom(id));
    }
}
