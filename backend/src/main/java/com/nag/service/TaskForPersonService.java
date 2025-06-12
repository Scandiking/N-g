package com.nag.service;

import com.nag.dto.TaskForPersonDTO;
import com.nag.mapper.TaskForPersonMapper;
import com.nag.model.TaskForPerson;
import com.nag.model.TaskForPersonId;
import com.nag.repository.TaskForPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing TaskForPerson relationships.
 * Handles business logic for assigning tasks to persons.
 *
 * @author Generated Service
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TaskForPersonService {

    private final TaskForPersonRepository taskForPersonRepository;
    private final TaskForPersonMapper taskForPersonMapper;

    /**
     * Get all task-person relationships.
     *
     * @return list of all TaskForPersonDTO
     */
    public List<TaskForPersonDTO> getAllTaskForPersons() {
        List<TaskForPerson> taskForPersons = taskForPersonRepository.findAll();
        return taskForPersonMapper.toTaskForPersonDTOs(taskForPersons);
    }

    /**
     * Get a specific task-person relationship by composite key.
     *
     * @param taskId the task ID
     * @param phoneNo the person's phone number
     * @return TaskForPersonDTO if found
     * @throws EntityNotFoundException if not found
     */
    public TaskForPersonDTO getTaskForPersonById(Integer taskId, String phoneNo) {
        TaskForPersonId id = new TaskForPersonId(taskId, phoneNo);
        TaskForPerson taskForPerson = taskForPersonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskForPerson not found with taskId: " + taskId + " and phoneNo: " + phoneNo));
        return taskForPersonMapper.toTaskForPersonDTO(taskForPerson);
    }

    /**
     * Get tasks assigned to a specific person by phone number.
     *
     * @param phoneNo the person's phone number
     * @return list of TaskForPersonDTO for the specified person
     */
    public List<TaskForPersonDTO> getTasksForPerson(String phoneNo) {
        List<TaskForPerson> taskForPersons = taskForPersonRepository.findByPhoneNo(phoneNo);
        return taskForPersonMapper.toTaskForPersonDTOs(taskForPersons);
    }

    /**
     * Get persons assigned to a specific task.
     *
     * @param taskId the task ID
     * @return list of TaskForPersonDTO for the specified task
     */
    public List<TaskForPersonDTO> getPersonsForTask(Integer taskId) {
        List<TaskForPerson> taskForPersons = taskForPersonRepository.findByTaskId(taskId);
        return taskForPersonMapper.toTaskForPersonDTOs(taskForPersons);
    }

    /**
     * Create a new task-person relationship (assign task to person).
     *
     * @param taskForPersonDTO the data to create
     * @return the created TaskForPersonDTO
     */
    public TaskForPersonDTO createTaskForPerson(TaskForPersonDTO taskForPersonDTO) {
        // Check if relationship already exists
        TaskForPersonId id = new TaskForPersonId(taskForPersonDTO.getTaskId(), taskForPersonDTO.getPhoneNo());
        if (taskForPersonRepository.existsById(id)) {
            throw new RuntimeException("Task is already assigned to this person: taskId=" +
                    taskForPersonDTO.getTaskId() + ", phoneNo=" + taskForPersonDTO.getPhoneNo());
        }

        TaskForPerson taskForPerson = taskForPersonMapper.toTaskForPerson(taskForPersonDTO);
        TaskForPerson savedTaskForPerson = taskForPersonRepository.save(taskForPerson);
        return taskForPersonMapper.toTaskForPersonDTO(savedTaskForPerson);
    }

    /**
     * Update an existing task-person relationship.
     *
     * @param taskId the task ID
     * @param phoneNo the person's phone number
     * @param taskForPersonDTO the updated data
     * @return the updated TaskForPersonDTO
     */
    public TaskForPersonDTO updateTaskForPerson(Integer taskId, String phoneNo, TaskForPersonDTO taskForPersonDTO) {
        TaskForPersonId id = new TaskForPersonId(taskId, phoneNo);

        TaskForPerson existingTaskForPerson = taskForPersonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskForPerson not found with taskId: " + taskId + " and phoneNo: " + phoneNo));

        // Since this is a junction table, there's not much to update
        // The relationship itself is the data
        TaskForPerson updatedTaskForPerson = taskForPersonRepository.save(existingTaskForPerson);
        return taskForPersonMapper.toTaskForPersonDTO(updatedTaskForPerson);
    }

    /**
     * Delete a task-person relationship (unassign task from person).
     *
     * @param taskId the task ID
     * @param phoneNo the person's phone number
     */
    public void deleteTaskForPerson(Integer taskId, String phoneNo) {
        TaskForPersonId id = new TaskForPersonId(taskId, phoneNo);

        if (!taskForPersonRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "TaskForPerson not found with taskId: " + taskId + " and phoneNo: " + phoneNo);
        }

        taskForPersonRepository.deleteById(id);
    }

    /**
     * Delete a task-person relationship by phone number only (for controller compatibility).
     *
     * @param phoneNo the person's phone number
     */
    public void deleteTaskForPerson(String phoneNo) {
        // This method is for controller compatibility but it's not ideal
        // since we need both taskId and phoneNo for the composite key
        List<TaskForPerson> taskForPersons = taskForPersonRepository.findByPhoneNo(phoneNo);
        taskForPersonRepository.deleteAll(taskForPersons);
    }

    /**
     * Get TaskForPersonDTO by phone number (for controller compatibility).
     *
     * @param phoneNo the person's phone number
     * @return first TaskForPersonDTO found for the person
     */
    public TaskForPersonDTO getTaskForPersonsById(String phoneNo) {
        List<TaskForPerson> taskForPersons = taskForPersonRepository.findByPhoneNo(phoneNo);
        if (taskForPersons.isEmpty()) {
            throw new EntityNotFoundException("No tasks found for person with phoneNo: " + phoneNo);
        }
        return taskForPersonMapper.toTaskForPersonDTO(taskForPersons.get(0));
    }

    /**
     * Remove all task assignments for a specific person.
     *
     * @param phoneNo the person's phone number
     */
    public void deleteAllTasksForPerson(String phoneNo) {
        taskForPersonRepository.deleteByPhoneNo(phoneNo);
    }

    /**
     * Remove all person assignments for a specific task.
     *
     * @param taskId the task ID
     */
    public void deleteAllPersonsForTask(Integer taskId) {
        taskForPersonRepository.deleteByTaskId(taskId);
    }

    /**
     * Check if a task-person relationship exists.
     *
     * @param taskId the task ID
     * @param phoneNo the person's phone number
     * @return true if relationship exists, false otherwise
     */
    public boolean existsTaskForPerson(Integer taskId, String phoneNo) {
        TaskForPersonId id = new TaskForPersonId(taskId, phoneNo);
        return taskForPersonRepository.existsById(id);
    }
}