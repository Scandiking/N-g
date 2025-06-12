package com.nag.controller;

/**
 * @description Controller for managing tasks assigned to persons.
 * @author Kristian
 */

import com.nag.dto.TaskForPersonDTO;
import com.nag.service.TaskForPersonService;
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
@RequestMapping("/api/taskforpersons")
@Tag(name = "Task For Person Management", description = "Operations related to managing tasks for persons")
@RequiredArgsConstructor
public class TaskForPersonController {
    private final TaskForPersonService taskForPersonService;

    @GetMapping
    @Operation(summary = "Get all tasks for persons", description = "Retrieve a list of all tasks assigned to persons")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks for persons"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TaskForPersonDTO>> getAllTaskForPersons() {
        return ResponseEntity.ok(taskForPersonService.getAllTaskForPersons());
    }

    @GetMapping("/{taskId}/{phoneNo}")
    @Operation(summary = "Get task assignment by task ID and phone number", description = "Retrieve a specific task assignment by task ID and phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved task assignment"),
            @ApiResponse(responseCode = "404", description = "Task assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TaskForPersonDTO> getTaskForPersonById(@PathVariable Integer taskId, @PathVariable String phoneNo) {
        return ResponseEntity.ok(taskForPersonService.getTaskForPersonById(taskId, phoneNo));
    }

    @PostMapping
    @Operation(summary = "Create a new task for person", description = "Create a new task assigned to a person with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created task for person"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TaskForPersonDTO> createTaskForPerson(@Valid @RequestBody TaskForPersonDTO taskForPersonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskForPersonService.createTaskForPerson(taskForPersonDTO));
    }

    @PutMapping("/{taskId}/{phoneNo}")
    @Operation(summary = "Update an existing task assignment", description = "Update an existing task assignment by task ID and phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated task assignment"),
            @ApiResponse(responseCode = "404", description = "Task assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TaskForPersonDTO> updateTaskForPerson(@PathVariable Integer taskId, @PathVariable String phoneNo, @Valid @RequestBody TaskForPersonDTO taskForPersonDTO) {
        return ResponseEntity.ok(taskForPersonService.updateTaskForPerson(taskId, phoneNo, taskForPersonDTO));
    }

    @DeleteMapping("/{taskId}/{phoneNo}")
    @Operation(summary = "Delete a task assignment", description = "Delete a task assignment by task ID and phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted task assignment"),
            @ApiResponse(responseCode = "404", description = "Task assignment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTaskForPerson(@PathVariable Integer taskId, @PathVariable String phoneNo) {
        taskForPersonService.deleteTaskForPerson(taskId, phoneNo);
        return ResponseEntity.noContent().build();
    }

    // Additional useful endpoints

    @GetMapping("/person/{phoneNo}")
    @Operation(summary = "Get all tasks for a person", description = "Retrieve all tasks assigned to a specific person")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks for person"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TaskForPersonDTO>> getTasksForPerson(@PathVariable String phoneNo) {
        return ResponseEntity.ok(taskForPersonService.getTasksForPerson(phoneNo));
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Get all persons for a task", description = "Retrieve all persons assigned to a specific task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved persons for task"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TaskForPersonDTO>> getPersonsForTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskForPersonService.getPersonsForTask(taskId));
    }
}