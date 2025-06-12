package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for TaskForPerson.
 * Used to transfer data regarding the assignment of tasks to persons.
 *
 * This represents the many-to-many relationship between Task and Person.
 * Composite key uses taskId and phoneNo to identify the assignment.
 *
 * @author Generated DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskForPersonDTO {

    /**
     * ID of the task being assigned
     */
    private Integer taskId;

    /**
     * Phone number of the person the task is assigned to
     */
    private String phoneNo;

    // Optional: Include nested objects for display purposes
    // These can be populated by the mapper when needed

    /**
     * Optional: Task details for display purposes
     * (Can be null if not needed)
     */
    private TaskDTO task;

    /**
     * Optional: Person details for display purposes
     * (Can be null if not needed)
     */
    private PersonDTO person;

    /**
     * Constructor for basic assignment (just IDs)
     *
     * @param taskId ID of the task
     * @param phoneNo phone number of the person
     */
    public TaskForPersonDTO(Integer taskId, String phoneNo) {
        this.taskId = taskId;
        this.phoneNo = phoneNo;
    }
}