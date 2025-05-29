package com.nag.controller;

/**
 * @description: Controller for managing notification frequency settings. Mest sannsynlig ubrukelig
 * @author Kristian
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//@RestController
//@RequestMapping("/api/notifreqfortasks")
//@Tag(name= "Notification Frequency for Tasks", description = "Operations related to managing notification frequency for tasks")
//@RequiredArgsConstructor
//public class NotiFreqForTaskController {
//    private final NotiFreqForTaskService notiFreqForTaskService;
//
//    // Get all notification frequencies for tasks
//    @GetMapping
//    @Operations(summary = "Get all notification frequencies for tasks", )
//               description = "Retrieve a list of all notification frequencies for tasks")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of notification frequencies for tasks"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<List<NotiFreqForTaskDTO>> getAllNotiFreqForTasks() {
//    return ResponseEntity.ok(notiFreqForTaskService.getAllNotiFreqForTasks());
//    }
//
//    // Get notification frequency for task by ID
//    @GetMapping("/{notiFreqId}")
//    @Operation(summary = "Get notification frequency for task by ID",
//            description = "Retrieve a notification frequency for a task by its ID")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Successfully retrieved notification frequency for task"),
//        @ApiResponse(responseCode = "404", description = "Notification frequency for task not found"),
//        @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    public ResponseEntity<NotiFreqForTaskDTO> getNotiFreqForTasksById(@PathVariable Short notiFreqId) {
//        return ResponseEntity.ok(notiFreqForTaskService.getNotiFreqForTasksById(notiFreqId));
//    }
//
//}
