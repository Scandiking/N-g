package com.nag.mapper;

import com.nag.dto.NotiFreqDTO;
import com.nag.model.NotiFreq;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotiFreqMapper {
    NotiFreqDTO toNotiFreqDTO(NotiFreq notiFreq);
    NotiFreq toNotiFreq(NotiFreqDTO notiFreqDTO);
    List<NotiFreqDTO> toNotiFreqDTOs(List<NotiFreq> notiFreqs);
}