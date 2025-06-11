package com.nag.service;

import com.nag.dto.NotiFreqForTaskDTO;
import com.nag.mapper.NotiFreqForTaskMapper;
import com.nag.model.NotiFreqForTask;
import com.nag.model.NotiFreqForTaskId;
import com.nag.repository.NotiFreqForTaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotiFreqForTaskService {

    private final NotiFreqForTaskRepository notiFreqForTaskRepository;
    private final NotiFreqForTaskMapper notiFreqForTaskMapper;

    // ✅ Kun basic CRUD operasjoner:

    public List<NotiFreqForTaskDTO> getAllNotiFreqForTasks() {
        List<NotiFreqForTask> notiFreqForTasks = notiFreqForTaskRepository.findAll();
        return notiFreqForTaskMapper.toNotiFreqForTaskDTOs(notiFreqForTasks);
    }

    public NotiFreqForTaskDTO getNotiFreqForTasksById(Integer taskId, Short notiFreqId) {
        NotiFreqForTaskId notiFreqForTaskId = new NotiFreqForTaskId(taskId, notiFreqId);

        NotiFreqForTask notiFreqForTask = notiFreqForTaskRepository.findById(notiFreqForTaskId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Notification frequency for task not found with taskId: " + taskId +
                                " and notiFreqId: " + notiFreqId));

        return notiFreqForTaskMapper.toNotiFreqForTaskDTO(notiFreqForTask);
    }

    public NotiFreqForTaskDTO createNotiFreqForTask(NotiFreqForTaskDTO notiFreqForTaskDTO) {
        NotiFreqForTaskId id = new NotiFreqForTaskId(
                notiFreqForTaskDTO.getTaskId(),
                notiFreqForTaskDTO.getNotiFreqId()
        );

        if (notiFreqForTaskRepository.existsById(id)) {
            throw new RuntimeException(
                    "Notification frequency association already exists for taskId: " +
                            notiFreqForTaskDTO.getTaskId() + " and notiFreqId: " + notiFreqForTaskDTO.getNotiFreqId()
            );
        }

        NotiFreqForTask notiFreqForTask = notiFreqForTaskMapper.toNotiFreqForTask(notiFreqForTaskDTO);
        NotiFreqForTask savedNotiFreqForTask = notiFreqForTaskRepository.save(notiFreqForTask);
        return notiFreqForTaskMapper.toNotiFreqForTaskDTO(savedNotiFreqForTask);
    }

    public NotiFreqForTaskDTO updateNotiFreqForTask(Integer taskId, Short notiFreqId, NotiFreqForTaskDTO notiFreqForTaskDTO) {
        NotiFreqForTaskId notiFreqForTaskId = new NotiFreqForTaskId(taskId, notiFreqId);

        NotiFreqForTask existingNotiFreqForTask = notiFreqForTaskRepository.findById(notiFreqForTaskId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Notification frequency for task not found with taskId: " + taskId +
                                " and notiFreqId: " + notiFreqId));

        return notiFreqForTaskMapper.toNotiFreqForTaskDTO(existingNotiFreqForTask);
    }

    public void deleteNotiFreqForTask(Integer taskId, Short notiFreqId) {
        NotiFreqForTaskId notiFreqForTaskId = new NotiFreqForTaskId(taskId, notiFreqId);

        if (!notiFreqForTaskRepository.existsById(notiFreqForTaskId)) {
            throw new EntityNotFoundException(
                    "Notification frequency for task not found with taskId: " + taskId +
                            " and notiFreqId: " + notiFreqId);
        }

        notiFreqForTaskRepository.deleteById(notiFreqForTaskId);
    }

    public boolean existsNotiFreqForTask(Integer taskId, Short notiFreqId) {
        NotiFreqForTaskId id = new NotiFreqForTaskId(taskId, notiFreqId);
        return notiFreqForTaskRepository.existsById(id);
    }
}