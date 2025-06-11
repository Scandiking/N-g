package com.nag.service;

import com.nag.dto.TaskDTO;
import com.nag.model.Task;
import com.nag.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskDTO> getAllTasksForUser(String creator) {
        return taskRepository.findByCreator(creator).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskDTO createTask(TaskDTO dto) {
        Task t = new Task();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setDueDate(dto.getDueDate());
        t.setNotiFreqId(dto.getNotiFreqId());
        t.setCreator(dto.getCreator());
        t.setCompleted(false);
        Task saved = taskRepository.save(t);
        return toDTO(saved);
    }

    @Transactional
    public TaskDTO markTaskDone(Integer id) {
        Task t = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        t.setCompleted(true);
        Task updated = taskRepository.save(t);
        return toDTO(updated);
    }

    private TaskDTO toDTO(Task t) {
        return new TaskDTO(
                t.getTaskId(),
                t.getTitle(),
                t.getDescription(),
                t.getDueDate(),
                t.getNotiFreqId(),
                t.getCreator(),
                t.isCompleted()
        );
    }
}
