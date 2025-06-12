package com.nag.repository;

import com.nag.model.TaskForPerson;
import com.nag.model.TaskForPersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for TaskForPerson entity.
 * Handles database operations for task-person assignments.
 *
 * Uses composite key (taskId and phoneNo) for entity identification.
 *
 * @author Generated Repository
 */
@Repository
public interface TaskForPersonRepository extends JpaRepository<TaskForPerson, TaskForPersonId> {

    /**
     * Find all task assignments for a specific person.
     *
     * @param phoneNo the person's phone number
     * @return list of TaskForPerson relationships for the specified person
     */
    List<TaskForPerson> findByPhoneNo(String phoneNo);

    /**
     * Find all person assignments for a specific task.
     *
     * @param taskId the task ID
     * @return list of TaskForPerson relationships for the specified task
     */
    List<TaskForPerson> findByTaskId(Integer taskId);

    /**
     * Delete all task assignments for a specific person.
     *
     * @param phoneNo the person's phone number
     */
    void deleteByPhoneNo(String phoneNo);

    /**
     * Delete all person assignments for a specific task.
     *
     * @param taskId the task ID
     */
    void deleteByTaskId(Integer taskId);

    /**
     * Check if a person has any task assignments.
     *
     * @param phoneNo the person's phone number
     * @return true if person has task assignments, false otherwise
     */
    boolean existsByPhoneNo(String phoneNo);

    /**
     * Check if a task has any person assignments.
     *
     * @param taskId the task ID
     * @return true if task has person assignments, false otherwise
     */
    boolean existsByTaskId(Integer taskId);

    /**
     * Count the number of tasks assigned to a specific person.
     *
     * @param phoneNo the person's phone number
     * @return number of tasks assigned to the person
     */
    long countByPhoneNo(String phoneNo);

    /**
     * Count the number of persons assigned to a specific task.
     *
     * @param taskId the task ID
     * @return number of persons assigned to the task
     */
    long countByTaskId(Integer taskId);
}