package com.nag.controller;

import com.nag.dto.TaskForPersonDTO;
import com.nag.service.TaskForPersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taskforpersons")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TaskForPersonController {

    private final TaskForPersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Assign a task to a person")
    public TaskForPersonDTO assign(@RequestBody TaskForPersonDTO dto) {
        return service.assignTaskToPerson(dto);
    }
}
