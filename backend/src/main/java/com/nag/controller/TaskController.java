/**
 * TaskController.java
 * Controller for managing task-related operations.
 */

package com.nag.controller;

import com.nag.model.AppUser;
import com.nag.model.Task;
import com.nag.repository.AppUserRepository;
import com.nag.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task
     * @return the task with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return taskRepository.findById(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new task.
     *
     * @param task the task to create
     * @return the created task with status 201
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication auth) {
        AppUser user = appUserRepository.
                findByUsername(auth.getName())
                .orElseThrow();
        task.setPerson(user.getPerson());
        task.setCreator(user.getPerson().getPhoneNo());
        Task saved = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Updates an existing task.
     *
     * @param id   the ID of the task to update
     * @param task the updated task data
     * @return the updated task with status 200, or 404 if not found
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
     *
     * @param id the ID of the task to delete
     * @return 204 No Content if successful, or 404 if not found
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
     *
     * @param id the ID of the task
     * @return true if the task exists, false otherwise
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> taskExists(@PathVariable Integer id) {
        boolean exists = taskRepository.existsById(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}