package com.nag.dto;

import com.nag.model.NotiFreqForTask;
import com.nag.model.NotiFreqForTaskId;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotiFreqForTaskDTO {
    private Integer taskId;
    private Short notiFreqId;

}
