/**
 * @description: Controller for managing notification frequency for task settings.
 * @author Kristian
 */

package com.nag.controller;

import com.nag.dto.NotiFreqForTaskDTO;
import com.nag.service.NotiFreqForTaskService;
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
@RequestMapping("/api/notifreqfortasks")
@Tag(name = "Notification Frequency for Tasks", description = "Operations related to managing notification frequency for tasks")
@RequiredArgsConstructor
public class NotiFreqForTaskController {
    private final NotiFreqForTaskService notiFreqForTaskService;

    // Get all notification frequencies for tasks
    @GetMapping
    @Operation(summary = "Get all notification frequencies for tasks",
            description = "Retrieve a list of all notification frequencies for tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of notification frequencies for tasks"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<NotiFreqForTaskDTO>> getAllNotiFreqForTasks() {
        return ResponseEntity.ok(notiFreqForTaskService.getAllNotiFreqForTasks());
    }

    // Get notification frequency for the task by ID
    @GetMapping("/{taskId}/{notiFreqId}")
    @Operation(summary = "Get notification frequency for task by ID",
            description = "Retrieve a notification frequency for a task by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved notification frequency for task"),
            @ApiResponse(responseCode = "404", description = "Notification frequency for task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotiFreqForTaskDTO> getNotiFreqForTasksById(@PathVariable Integer taskId, @PathVariable Short notiFreqId) {
        return ResponseEntity.ok(notiFreqForTaskService.getNotiFreqForTasksById(taskId, notiFreqId));
    }

    @PostMapping
    @Operation(summary = "Create a new NotiFreqForTask")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid entity data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotiFreqForTaskDTO> createNotiFreqForTask(@Valid @RequestBody NotiFreqForTaskDTO notiFreqForTaskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notiFreqForTaskService.createNotiFreqForTask(notiFreqForTaskDTO));
    }

    @PutMapping("/{taskId}/{notiFreqId}")
    @Operation(summary = "Update the notification frequency for a task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update successful"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<NotiFreqForTaskDTO> updateNotiFreqForTask(@PathVariable Integer taskId, @PathVariable Short notiFreqId, @Valid @RequestBody NotiFreqForTaskDTO notiFreqForTaskDTO) {
        return ResponseEntity.ok(notiFreqForTaskService.updateNotiFreqForTask(taskId, notiFreqId, notiFreqForTaskDTO));
    }

    @DeleteMapping("{taskId}/{notiFreqId}")
    @Operation(summary = "Delete a product")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletion success"),
            @ApiResponse(responseCode = "404", description = "NotiFreqForTask not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteNotiFreqForTask(@PathVariable Integer taskId, @PathVariable Short notiFreqId) {
        notiFreqForTaskService.deleteNotiFreqForTask(taskId, notiFreqId);
        return ResponseEntity.noContent().build();
    }

}
