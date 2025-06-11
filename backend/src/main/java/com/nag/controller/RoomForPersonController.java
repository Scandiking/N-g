// src/main/java/com/nag/controller/RoomForPersonController.java
package com.nag.controller;

import com.nag.dto.RoomForPersonDTO;
import com.nag.service.RoomForPersonService;
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
@RequestMapping("/api/roomforpersons")
@Tag(name = "Room For Person Management", description = "Operations related to managing room–person assignments")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class RoomForPersonController {

    private final RoomForPersonService service;

    @GetMapping
    @Operation(summary = "Get all room–person assignments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of assignments"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<RoomForPersonDTO>> getAll() {
        return ResponseEntity.ok(service.getAllRoomForPersons());
    }

    @GetMapping("/{roomId}/{phoneNo}")
    @Operation(summary = "Get one room–person assignment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assignment"),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomForPersonDTO> getOne(
            @PathVariable Short roomId,
            @PathVariable String phoneNo
    ) {
        RoomForPersonDTO dto = service.getRoomForPersonById(roomId, phoneNo);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new room–person assignment")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created assignment"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomForPersonDTO> create(
            @Valid @RequestBody RoomForPersonDTO dto
    ) {
        RoomForPersonDTO created = service.createRoomForPerson(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{roomId}/{phoneNo}")
    @Operation(summary = "Update an existing room–person assignment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated assignment"),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomForPersonDTO> update(
            @PathVariable Short roomId,
            @PathVariable String phoneNo,
            @Valid @RequestBody RoomForPersonDTO dto
    ) {
        RoomForPersonDTO updated = service.updateRoomForPerson(roomId, phoneNo, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{roomId}/{phoneNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a room–person assignment")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted assignment"),
            @ApiResponse(responseCode = "404", description = "Assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> delete(
            @PathVariable Short roomId,
            @PathVariable String phoneNo
    ) {
        service.deleteRoomForPerson(roomId, phoneNo);
        return ResponseEntity.noContent().build();
    }
}
