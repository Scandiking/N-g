package com.nag.mapper;

import com.nag.dto.RoomForPersonDTO;
import com.nag.model.RoomForPerson;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomForPersonMapper {
    RoomForPersonDTO toRoomForPersonDTO(RoomForPerson roomForPerson);
    RoomForPerson toRoomForPerson(RoomForPersonDTO roomForPersonDTO);
    List<RoomForPersonDTO> toRoomForPersonDTOs(List<RoomForPerson> roomForPersons);
}
