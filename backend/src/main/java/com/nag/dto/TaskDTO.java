/**
 * Data Transfer Object (DTO) for Task.
 *
 * @description This class is used to transfer task data between different layers of the application.
 * @author Kristian
 */

package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private short notiFreqId;
    private String creator;
    private boolean completed;

    // There is no need for setters or getters as Lombok's @Data annotation generates them automatically.
}
