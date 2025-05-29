package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for Task.
 * @description This class is used to transfer task data between different layers of the application.
 * @author Kristian
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer taskId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;


}
