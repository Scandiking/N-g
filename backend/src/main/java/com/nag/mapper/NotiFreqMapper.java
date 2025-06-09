/**
 * @description Routing and request handling of notification frequency table
 * @author Kristan
 */

package com.nag.mapper;

import com.nag.dto.NotiFreqDTO;
import com.nag.model.NotiFreq;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface NotiFreqMapper {
    NotiFreqDTO toNotiFreqDTO(NotiFreq notiFreq);

    List<NotiFreqDTO> toNotiFreqDTOs(List<NotiFreq> notiFreqs);
}
