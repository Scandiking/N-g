package com.nag.mapper;

import com.nag.dto.RoomDTO;
import com.nag.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    RoomDTO toRoomDTO(Room room);

    List<RoomDTO> toRoomDTOs(List<Room> rooms);

    // Custom mapping methods here
}
