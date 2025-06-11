// src/main/java/com/nag/service/TaskForPersonService.java
package com.nag.service;

import com.nag.dto.TaskForPersonDTO;
import com.nag.model.TaskForPerson;
import com.nag.repository.TaskForPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskForPersonService {

    private final TaskForPersonRepository repository;

    @Transactional
    public TaskForPersonDTO assignTaskToPerson(TaskForPersonDTO dto) {
        // Use the two-arg constructor (sets taskId & phoneNo)
        TaskForPerson entity = new TaskForPerson(dto.getTaskId(), dto.getPhoneNo());
        repository.save(entity);
        return dto;
    }
}
