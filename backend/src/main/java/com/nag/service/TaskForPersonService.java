package com.nag.service;

import com.nag.dto.TaskForPersonDTO;
import com.nag.model.TaskForPerson;
import com.nag.model.TaskForPersonId;
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
        TaskForPersonId id = new TaskForPersonId(dto.getTaskId(), dto.getPhoneNo());
        TaskForPerson entity = new TaskForPerson();
        entity.setId(id);
        repository.save(entity);
        return dto;
    }
}
