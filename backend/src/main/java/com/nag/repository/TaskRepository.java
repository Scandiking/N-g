package com.nag.repository;

import com.nag.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    // Spring Data JPA gir deg automatisk findAll(), findById(), save(), etc.
}