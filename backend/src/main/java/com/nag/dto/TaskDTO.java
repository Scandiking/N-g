// src/main/java/com/nag/dto/TaskDTO.java
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
    private LocalDateTime dueDate;   // changed from LocalDate
    private Short notiFreqId;
    private String creator;
    private boolean completed;
}
