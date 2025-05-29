//package com.nag.controller;
//
/**
 * @description Controller for managing tasks assigned to persons.
 * @author Kristian
 */
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/taskforpersons")
//@Tag(name = "Task For Person Management", description = "Operations related to managing tasks for persons")
//@RequiredArgsConstructor
//public class TaskForPersonController {
//    private final TaskForPersonService taskForPersonService;
//
//    @GetMapping
//    @Operation(summary = "Get all tasks for persons", description = "Retrieve a list of all tasks assigned to persons")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks for persons"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<List<TaskForPersonDTO>> getAllTaskForPersons() {
//        return ResponseEntity.ok(taskForPersonService.getAllTaskForPersons());
//    }
//
//    @GetMapping("/{phoneNo}")
//    @Operation(summary = "Get tasks for person by phone number", description = "Retrieve tasks assigned to a person by their phone number")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks for person"),
//        @ApiResponse(responseCode = "404", description = "Person not found"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<TaskForPersonDTO> getTaskForPersonsById(@PathVariable String phoneNo) {
//        return ResponseEntity.ok(taskForPersonService.getTaskForPersonsById(phoneNo));
//    }
//
//    @PostMapping
//    @Operation(summary = "Create a new task for person", description = "Create a new task assigned to a person with the provided details")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses({
//        @ApiResponse(responseCode = "201", description = "Successfully created task for person"),
//        @ApiResponse(responseCode = "400", description = "Invalid input data"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<TaskForPersonDTO> createTaskForPerson(@Valid @RequestBody TaskForPersonDTO taskForPersonDTO) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(taskForPersonService.createTaskForPerson(taskForPersonDTO));
//    }
//
//    @PutMapping("/{phoneNo}")
//    @Operation(summary = "Update an existing task for person", description = "Update an existing task assigned to a person by their phone number")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Successfully updated task for person"),
//        @ApiResponse(responseCode = "404", description = "Person not found"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<TaskForPersonDTO> updateTaskForPerson(@PathVariable String phoneNo, @Valid @RequestBody TaskForPersonDTO taskForPersonDTO) {
//        return ResponseEntity.ok(taskForPersonService.updateTaskForPerson(phoneNo, taskForPersonDTO));
//    }
//
//    @DeleteMapping("/{phoneNo}")
//    @Operation(summary = "Delete a task for person", description = "Delete a task assigned to a person by their phone number")
//    @ApiResponses({
//        @ApiResponse(responseCode = "204", description = "Successfully deleted task for person"),
//        @ApiResponse(responseCode = "404", description = "Person not found"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<Void> deleteTaskForPerson(@PathVariable String phoneNo) {
//        taskForPersonService.deleteTaskForPerson(phoneNo);
//        return ResponseEntity.noContent().build();
//    }
//
//}
