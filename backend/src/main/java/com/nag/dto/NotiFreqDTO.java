package com.nag.dto;

/**
 * Data Transfer Object (DTO) for Task.
 * @description This class is used to transfer notification frequency data between different layers of the application.
 * @author Kristian
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotiFreqDTO {
    private short notiFreqId;
    private String notiFreqTitle;
    private String baseInterval;
    private float growthFactor;
    private short maxRepeats;

    // No need for setters or getters as Lombok's @Data annotation generates them automatically
}
