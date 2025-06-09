/**
 * @description
 * @author Kristian
 */
package com.nag.service;

import com.nag.dto.NotiFreqForTaskDTO;
import com.nag.mapper.NotiFreqForTaskMapper;
import com.nag.model.NotiFreqForTask;
import com.nag.model.NotiFreqForTaskId;
import com.nag.repository.NotiFreqForTaskRepository;
import com.nag.repository.NotiFreqRepository;
import com.nag.repository.TaskRepository;
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

    // Get all notification frequencies
    public List<NotiFreqForTaskDTO> getAllNotiFreqForTasks() {
        List<NotiFreqForTask> notiFreqForTasks = notiFreqForTaskRepository.findAll();
        return notiFreqForTaskMapper.toNotiFreqForTaskDTOs(notiFreqForTasks);
    }

    // Get notification frequency by id
    public NotiFreqForTaskDTO getNotiFreqForTasksById(Integer taskId, Short notiFreqId) {

        NotiFreqForTaskId notiFreqForTaskId = new NotiFreqForTaskId(taskId, notiFreqId);

        NotiFreqForTask notiFreqForTask = notiFreqForTaskRepository.findById(notiFreqForTaskId)
                .orElseThrow(() -> new EntityNotFoundException("Notification frequency for task not found"));
        return notiFreqForTaskMapper.toNotiFreqForTaskDTO(notiFreqForTask);
    }

    // Create a new notifreq for task
    public NotiFreqForTaskDTO createNotiFreqForTask(NotiFreqForTaskDTO notiFreqForTaskDTO) {
        NotiFreqForTask notiFreqForTask = new NotiFreqForTask();

        notiFreqForTask.setTaskId(notiFreqForTaskDTO.getTaskId());
        notiFreqForTask.setNotiFreqId(notiFreqForTaskDTO.getNotiFreqId());

        NotiFreqForTask updatedNotiFreqForTask = notiFreqForTaskRepository.save(notiFreqForTask);
        return notiFreqForTaskMapper.toNotiFreqForTaskDTO(updatedNotiFreqForTask);
    }

    // Update a new notification frequency for task
    public NotiFreqForTaskDTO updateNotiFreqForTask(Integer taskId, Short notiFreqId, NotiFreqForTaskDTO notiFreqForTaskDTO) {
        NotiFreqForTaskId notiFreqForTaskId = new NotiFreqForTaskId(taskId, notiFreqId);

        NotiFreqForTask notiFreqForTask = notiFreqForTaskRepository.findById(notiFreqForTaskId)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        notiFreqForTask.setTaskId(notiFreqForTaskDTO.getTaskId());
        notiFreqForTask.setNotiFreqId(notiFreqForTaskDTO.getNotiFreqId());

        NotiFreqForTask updatedNotiFreqForTask = notiFreqForTaskRepository.save(notiFreqForTask);
        return notiFreqForTaskMapper.toNotiFreqForTaskDTO(updatedNotiFreqForTask);
    }

    // Dlete a notificatin frequency for task
    public void deleteNotiFreqForTask(Integer taskId, Short notiFreqId) {
        NotiFreqForTaskId notiFreqForTaskId = new NotiFreqForTaskId(taskId, notiFreqId);
        if (!notiFreqForTaskRepository.existsById(notiFreqForTaskId)) {
            throw new EntityNotFoundException("Notification frequency for task not found");
        }
        notiFreqForTaskRepository.deleteById(notiFreqForTaskId);
    }




}
