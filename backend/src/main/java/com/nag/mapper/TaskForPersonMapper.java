package com.nag.mapper;

import com.nag.dto.TaskForPersonDTO;
import com.nag.model.TaskForPerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * MapStruct mapper for converting between TaskForPerson entity and TaskForPersonDTO.
 * Handles the mapping of task-person assignment relationships.
 *
 * @author Generated Mapper
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskForPersonMapper {

    /**
     * Convert TaskForPerson entity to TaskForPersonDTO.
     * Maps both basic fields and nested objects.
     *
     * @param taskForPerson the entity to convert
     * @return the corresponding DTO
     */
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "phoneNo", target = "phoneNo")
    @Mapping(source = "task", target = "task")
    @Mapping(source = "person", target = "person")
    TaskForPersonDTO toTaskForPersonDTO(TaskForPerson taskForPerson);

    /**
     * Convert TaskForPersonDTO to TaskForPerson entity.
     * Maps basic fields but ignores nested objects since they're handled by JPA relationships.
     *
     * @param taskForPersonDTO the DTO to convert
     * @return the corresponding entity
     */
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "phoneNo", target = "phoneNo")
    @Mapping(target = "task", ignore = true)  // Let JPA handle the relationship
    @Mapping(target = "person", ignore = true)  // Let JPA handle the relationship
    TaskForPerson toTaskForPerson(TaskForPersonDTO taskForPersonDTO);

    /**
     * Convert list of TaskForPerson entities to list of TaskForPersonDTOs.
     *
     * @param taskForPersons the entities to convert
     * @return the corresponding DTOs
     */
    List<TaskForPersonDTO> toTaskForPersonDTOs(List<TaskForPerson> taskForPersons);

    /**
     * Convert list of TaskForPersonDTOs to list of TaskForPerson entities.
     *
     * @param taskForPersonDTOs the DTOs to convert
     * @return the corresponding entities
     */
    List<TaskForPerson> toTaskForPersons(List<TaskForPersonDTO> taskForPersonDTOs);
}