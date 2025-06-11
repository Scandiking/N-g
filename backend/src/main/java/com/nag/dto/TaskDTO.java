package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer taskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Short notiFreqId;
    private String creator;
    private boolean completed;
}
