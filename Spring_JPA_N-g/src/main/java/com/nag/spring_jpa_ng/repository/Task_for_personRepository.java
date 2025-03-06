package com.nag.spring_jpa_ng.repository;

import java.util.List;

import com.nag.spring_jpa_ng.model.Task_for_person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Task_for_personRepository extends JpaRepository<Task_for_person, Long> {
    <List> Task_for_person findByTaskId(Long taskId);
    <List> Task_for_person findByPersonId(Long personId);
}
