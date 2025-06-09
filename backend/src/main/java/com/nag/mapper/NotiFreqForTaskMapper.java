/**
 * @description Routing and request handling of notification frequency for task table
 * @author Kristan
 */

package com.nag.mapper;

import com.nag.dto.NotiFreqForTaskDTO;
import com.nag.model.NotiFreqForTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotiFreqForTaskMapper {
    NotiFreqForTaskMapper INSTANCE = Mappers.getMapper(NotiFreqForTaskMapper.class);

    NotiFreqForTaskDTO toNotiFreqForTaskDTO(NotiFreqForTask notiFreqForTask);

    NotiFreqForTask toNotiFreqForTask(NotiFreqForTaskDTO notiFreqForTaskDTO);

    List<NotiFreqForTaskDTO> toNotiFreqForTaskDTOs(List<NotiFreqForTask> notiFreqForTasks);
}
