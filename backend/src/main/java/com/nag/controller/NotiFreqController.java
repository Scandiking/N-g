package com.nag.controller;

import com.nag.dto.NotiFreqDTO;
import com.nag.model.NotiFreq;
import com.nag.service.NotiFreqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: Controller for managing notification frequency settings.
 * @author Kristian
 */

@RestController
@RequestMapping("/api/noti-freq")
@Tag(name="Notification frequency controller", description="Operations related to notification frequency settings")
@RequiredArgsConstructor
public class NotiFreqController {
    private final NotiFreqService notiFreqService;

    @GetMapping
    @Operation(summary="Get all notification frequencies", description="Retrieve a list of all notification frequency settings")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved notification frequencies"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<NotiFreqDTO>> getAllNotiFreq() {
        return ResponseEntity.ok(notiFreqService.getAllNotiFreqs());
    }

    @GetMapping("/{notiFreqId}")
    @Operation(summary="Get notification frequency by ID", description="Retrieve a notification frequency setting by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved notification frequency"),
        @ApiResponse(responseCode = "404", description = "Notification frequency not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotiFreqDTO> getAllNotiFreqById(@PathVariable Long notiFreqId) {
        return ResponseEntity.ok(notiFreqService.getNotiFreqById(notiFreqId.shortValue()));

    }

    @PostMapping
    @Operation(summary="Create a new notification frequency", description="Create a new notification frequency setting")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created notification frequency"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotiFreqDTO> createNotiFreq(@RequestBody NotiFreqDTO notiFreqDTO) {
        NotiFreqDTO createdNotiFreq = notiFreqService.createNotiFreq(notiFreqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotiFreq);
    }

    @PutMapping("/{notiFreqId}")
    @Operation(summary="Update an existing notification frequency", description="Update a notification frequency setting by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully updated notification frequency"),
        @ApiResponse(responseCode = "404", description = "Notification frequency not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotiFreqDTO> updateNotiFreq(@PathVariable Long notiFreqId, @RequestBody NotiFreqDTO notiFreqDTO) {
        NotiFreqDTO updatedNotiFreq = notiFreqService.updateNotiFreq(notiFreqId.shortValue(), notiFreqDTO);
        return ResponseEntity.ok(updatedNotiFreq);
    }

    @DeleteMapping("/{notiFreqId}")
    @Operation(summary="Delete a notification frequency", description="Delete a notification frequency setting by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Successfully deleted notification frequency"),
        @ApiResponse(responseCode = "404", description = "Notification frequency not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteNotiFreq(@PathVariable Long notiFreqId) {
        notiFreqService.deleteNotiFreq(notiFreqId.shortValue());
        return ResponseEntity.noContent().build();
    }




}
