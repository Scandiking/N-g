package com.nag.spring_jpa_ng.repository;

import java.util.List;
import java.util.Optional;


import com.nag.spring_jpa_ng.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional <Task> findByTaskId(Long id);
    List <Task> findByTaskName(String taskName);
    List <Task> findByTaskDescription(String taskDescription);
    List <Task> findByCreator(String taskCreator);

}
