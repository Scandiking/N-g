package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for RoomForPerson association.
 * Used to transfer data regarding the relation between Room and Person, including the score.
 *
 * Composite key uses roomId and phoneNo to identify the association.
 *
 * @author Jonas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomForPersonDTO {


    private Short roomId;
    private String phoneNo;
    private Short score;
    private RoomDTO room;
    private PersonDTO person;
}
