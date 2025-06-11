package com.nag.mapper;

import com.nag.dto.NotiFreqForTaskDTO;
import com.nag.model.NotiFreqForTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotiFreqForTaskMapper {

    // ✅ Map kun taskId og notiFreqId, ignorer resten
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "notiFreqId", target = "notiFreqId")
    NotiFreqForTaskDTO toNotiFreqForTaskDTO(NotiFreqForTask notiFreqForTask);

    // ✅ For reverse mapping er det lettere siden DTO kun har de feltene vi trenger
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "notiFreqId", target = "notiFreqId")
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "notiFreq", ignore = true)
    NotiFreqForTask toNotiFreqForTask(NotiFreqForTaskDTO notiFreqForTaskDTO);

    List<NotiFreqForTaskDTO> toNotiFreqForTaskDTOs(List<NotiFreqForTask> notiFreqForTasks);
}