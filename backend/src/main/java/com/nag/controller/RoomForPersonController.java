/**
 * @author Kristian
 * @description Controller for managing notification frequency settings.
 * This class handles HTTP requests related to notification frequency.
 */

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
@Tag(name = "Room For Person Management", description = "Operations related to managing room for persons")
@RequiredArgsConstructor
public class RoomForPersonController {
    private final RoomForPersonService roomForPersonService;


    @GetMapping
    @Operation(summary = "Get all room for persons", description = "Retrieve a list of all room for persons")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of room for persons"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<RoomForPersonDTO>> getAllRoomForPersons() {
        return ResponseEntity.ok(roomForPersonService.getAllRoomForPersons());
    }

    @GetMapping("/{roomId}/{phoneNo}")
    @Operation(summary = "Get room for person by ID", description = "Retrieve a room for person by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved room for person"),
            @ApiResponse(responseCode = "404", description = "Room for person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomForPersonDTO> getRoomForPersonById(@PathVariable Short roomId, @PathVariable String phoneNo) {
        RoomForPersonDTO roomForPersonDTO = roomForPersonService.getRoomForPersonById(roomId, phoneNo);
        return ResponseEntity.ok(roomForPersonDTO);
    }

    //
    // Method to POST a new RoomForPerson
    //
    @PostMapping
    @Operation(summary = "Create a new roomForPerson", description = "Create a new room for person with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created room for person"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomForPersonDTO> createRoomForPerson(@Valid @RequestBody RoomForPersonDTO roomForPersonDTO) {
        RoomForPersonDTO createdRoomForPerson = roomForPersonService.createRoomForPerson(roomForPersonDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomForPersonService.createRoomForPerson(roomForPersonDTO));
    }

    //
    // Method to update an existing RoomForPerson
    //
    @PutMapping("/{roomId}/{personId}")
    @Operation(summary = "Update an existing room for person", description = "Update the details of an existing room for person")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated room for person"),
            @ApiResponse(responseCode = "404", description = "Room for person not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoomForPersonDTO> updateRoomForPerson(@PathVariable Short roomId, @PathVariable String personId, @Valid @RequestBody RoomForPersonDTO roomForPersonDTO) {
        RoomForPersonDTO updatedRoomForPerson = roomForPersonService.updateRoomForPerson(roomId, personId, roomForPersonDTO);
        return ResponseEntity.ok(updatedRoomForPerson);
    }

    //
    // Method to delete a RoomForPerson
    //
    @DeleteMapping("/{roomForPersonId}")
    @Operation(summary = "Delete a room for person", description = "Delete a room for person by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted room for person"),
            @ApiResponse(responseCode = "404", description = "Room for person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteRoomForPerson(@PathVariable Short roomId, @PathVariable String personId) {
        roomForPersonService.deleteRoomForPerson(roomId, personId);
        return ResponseEntity.noContent().build();
    }
}
