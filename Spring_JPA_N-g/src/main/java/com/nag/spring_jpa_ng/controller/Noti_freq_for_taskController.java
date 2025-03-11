package com.nag.spring_jpa_ng.controller;

import com.nag.spring_jpa_ng.model.Person;
import com.nag.spring_jpa_ng.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins="http://localhost:4321")
@RestController
@RequestMapping("/api")

// Claimed by Jonas.
public class Noti_freq_for_taskController {

    @Autowired
    Noti_freq_for_taskRepository noti_freq_for_taskRepository;

    // Get all noti_freq_for_tasks
    @GetMapping("/noti_freq_for_tasks")
    public List<Noti_freq_for_task> getAllNoti_freq_for_tasks() { return noti_freq_for_taskRepository.findAll(); }


    // Get noti_freq_for_task by task id
    @GetMapping ("/noti_freq_for_tasks/taskId/{taskId}")
    public Noti_freq_for_task getNoti_freq_for_taskByTaskId(@PathVariable(value = "taskId") int taskId) {
        return noti_freq_for_taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Noti_freq_for_task not found with task id: " + taskId + "\n" +
                        "Please check the task id and try again.")); }

    // Get noti_freq_for_task by notification frequency id
    @GetMapping ("/noti_freq_for_tasks/notiFreqId/{notiFreqId}")
    public Noti_freq_for_task getNoti_freq_for_taskByNotiFreqId(@PathVariable(value = "notiFreqId") int notiFreqId) {
        return noti_freq_for_taskRepository.findByNotiFreqId(notiFreqId)
                .orElseThrow(() -> new RuntimeException("Noti_freq_for_task not found with notification frequency id: " + notiFreqId + "\n" +
                        "Please check the notification frequency id and try again.")); }


}
