/**
 * TaskController.java
 *
 * Controller for managing task-related operations
 */

package com.java.nag.controller;

import com.java.nag.model.Task;
import com.java.nag.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Get all tasks
     *
     * @return a list of all tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Get task from id
     *
     * @param id of the task
     * @return task with corresponding id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all tasks with a due date after a given time
     *
     * @param due date and time for task
     * @return a list of tasks with due date after the input date
     *
     * Exaple: GET /tasks/upcoming?after=2025-06-01T12:00:00
     */
    @GetMapping("/upcoming")
    public ResponseEntity<List<Task>> getUpcomingTasks(
            @RequestParam("after") LocalDateTime after
    ) {
        List<Task> upcoming = taskService.getTasksDueAfter(after);
        return ResponseEntity.ok(upcoming);
    }

    /**
     * Get all tasks that are not completed
     *
     * @return a list containing all tasks where the completed attribute is false
     */
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getIncompleteTasks() {
        List<Task> incomplete = taskService.getIncompleteTasks();
        return ResponseEntity.ok(incomplete);
    }

    /**
     * Create a new task
     *
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        Task saved = taskService.createTask(newTask);
        return ResponseEntity.ok(saved);
    }

    /**
     * Update an existing task
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Integer id,
            @RequestBody Task updatedTask
    ) {
        return taskService.updateTask(id, updatedTask)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

