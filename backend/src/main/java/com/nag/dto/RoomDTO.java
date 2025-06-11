package com.nag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Person.
 *  This class is used to transfer person data between different layers of the application.
 *
 *
 *
 * @author Kristian
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Short roomId;
    private String roomName;
    private String roomDescr;
    private String roomAdmin;
    private byte[] roomPicture;

    // No need for setters or getters as Lombok's @Data annotation generates them automatically

}
