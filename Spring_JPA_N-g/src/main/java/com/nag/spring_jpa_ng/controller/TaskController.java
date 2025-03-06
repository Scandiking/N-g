package com.nag.spring_jpa_ng.controller;
import com.nag.spring_jpa_ng.model.Task;
import com.nag.spring_jpa_ng.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4321")
@RestController
@RequestMapping("/api")

public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    // Get all tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by id
    @GetMapping("/tasks/id/{id}")
    public Task findByTaskId(@PathVariable(value = "id") Long taskId) {
        return taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId + "\n" +
                        "Please check the id and try again."));
    }

    // Get task by name
    @GetMapping("/tasks/name/{taskName}")
    public List<Task> findByTaskName(@PathVariable(value = "taskName") String taskName) {
        return taskRepository.findByTaskName(taskName);
    }

    // Get task by description
    @GetMapping("/tasks/descr/{taskDescr}")
    public List<Task> findByTaskDescription(@PathVariable(value = "taskDescr") String taskDescr) {
        return taskRepository.findByTaskDescription(taskDescr);
    }

    // Get task by creator
    @GetMapping("/tasks/creator/{taskCreator}")
    public List<Task> findByCreator(@PathVariable(value = "taskCreator") String taskCreator) {
        return taskRepository.findByCreator(taskCreator);
    }
}
