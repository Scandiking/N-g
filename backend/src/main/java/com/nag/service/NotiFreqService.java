/**
 * @description Service class for managing notification frequency settings.
 * This class will handle business logic related to notification frequencies,
 * such as creating, updating, deleting, and retrieving notification frequency settings.
 * @author Kristian
 */

package com.nag.service;

import com.nag.dto.NotiFreqDTO;
import com.nag.mapper.NotiFreqMapper;
import com.nag.model.NotiFreq;
import com.nag.repository.NotiFreqRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
@Transactional
@RequiredArgsConstructor
public class NotiFreqService {
    private final NotiFreqRepository notiFreqRepository;
    private final NotiFreqMapper notiFreqMapper; // Mapper not made yet, therefore red

    // Get all Notification Frequencies
    public List<NotiFreqDTO> getAllNotiFreqs() {
        List<NotiFreq> notiFreqs = notiFreqRepository.findAll();
        return notiFreqMapper.toNotiFreqDTOs(notiFreqs);
    }

    // Get Notification Frequency by ID
    public NotiFreqDTO getNotiFreqById(Short notiFreqId) {
        NotiFreq notiFreq = notiFreqRepository.findById(notiFreqId)
                .orElseThrow(() -> new EntityNotFoundException("Notification Frequency not found"));
        return notiFreqMapper.toNotiFreqDTO(notiFreq);
    }

    // Create a new Notification Frequency
    public NotiFreqDTO createNotiFreq(NotiFreqDTO notiFreqDTO) {
        NotiFreq notiFreq = new NotiFreq();
        notiFreq.setNotiFreqTitle(notiFreqDTO.getNotiFreqTitle());
        notiFreq.setBaseInterval(notiFreqDTO.getBaseInterval());
        notiFreq.setGrowthFactor(notiFreqDTO.getGrowthFactor());
        notiFreq.setMaxRepeats(notiFreqDTO.getMaxRepeats());

        NotiFreq savedNotiFreq = notiFreqRepository.save(notiFreq);
        return notiFreqMapper.toNotiFreqDTO(savedNotiFreq);
    }

    // Update an existing Notification Frequency
    public NotiFreqDTO updateNotiFreq(Short notiFreqId, NotiFreqDTO notiFreqDTO) {
        NotiFreq notiFreq = notiFreqRepository.findById(notiFreqId)
                .orElseThrow(() -> new EntityNotFoundException("Notification Frequency not found"));

        notiFreq.setNotiFreqTitle(notiFreqDTO.getNotiFreqTitle());
        notiFreq.setBaseInterval(notiFreqDTO.getBaseInterval());
        notiFreq.setGrowthFactor(notiFreqDTO.getGrowthFactor());
        notiFreq.setMaxRepeats(notiFreqDTO.getMaxRepeats());

        NotiFreq updatedNotiFreq = notiFreqRepository.save(notiFreq);
        return notiFreqMapper.toNotiFreqDTO(updatedNotiFreq);
    }

    // Delete a Notification Frequency
    public void deleteNotiFreq(Short notiFreqId) {
        NotiFreq notiFreq = notiFreqRepository.findById(notiFreqId)
                .orElseThrow(() -> new EntityNotFoundException("Notification Frequency not found"));
        notiFreqRepository.delete(notiFreq);
    }

}
