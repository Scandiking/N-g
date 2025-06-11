package com.nag.repository;

import com.nag.model.TaskForPerson;
import com.nag.model.TaskForPersonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskForPersonRepository
        extends JpaRepository<TaskForPerson, TaskForPersonId> {
}
