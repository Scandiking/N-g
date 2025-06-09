/**
 * Data Transfer Object (DTO) for RoomForPerson association.
 * Used to transfer data regarding the relation between Room and Person, including the score.
 * <p>
 * Composite key uses roomId and phoneNo to identify the association.
 *
 * @author Jonas.
 */

package com.nag.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomForPersonDTO {
    private Short roomId;
    private String phoneNo;
    private Short score;
    private RoomDTO room;
    private PersonDTO person;
}