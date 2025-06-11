// src/main/java/com/nag/controller/TaskController.java
package com.nag.controller;

import com.nag.model.AppUser;
import com.nag.model.Task;
import com.nag.repository.AppUserRepository;
import com.nag.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    /**
     * Retrieves all tasks.
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieves a task by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return taskRepository.findById(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new task.
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication auth) {
        AppUser user = appUserRepository
                .findByUsername(auth.getName())
                .orElseThrow();
        task.setPerson(user.getPerson());
        task.setCreator(user.getPerson().getPhoneNo());
        Task saved = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Updates an existing task.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        if (taskRepository.existsById(id)) {
            task.setTaskId(id);
            Task updatedTask = taskRepository.save(task);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a task by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Checks if a task exists by its ID.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> taskExists(@PathVariable Integer id) {
        boolean exists = taskRepository.existsById(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    /**
     * Marks a task as completed.
     */
    @PutMapping("/{id}/done")
    public ResponseEntity<Task> markTaskDone(@PathVariable Integer id) {
        Task t = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        t.setCompleted(true);
        Task saved = taskRepository.save(t);
        return ResponseEntity.ok(saved);
    }
}
