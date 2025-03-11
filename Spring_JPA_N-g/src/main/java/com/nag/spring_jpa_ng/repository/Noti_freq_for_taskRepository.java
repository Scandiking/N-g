package com.nag.spring_jpa_ng.repository;

import java.util.List;

import com.nag.spring_jpa_ng.model.Noti_freq_for_task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Noti_freq_for_taskRepository extends JpaRepository<Noti_freq_for_task, Long> {
    List <Noti_freq_for_task> findByTaskId(int taskId);
    List <Noti_freq_for_task> findByNotiFreqId(int notiFreqId);

}