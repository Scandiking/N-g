package com.nag.repository;

import com.nag.model.NotiFreqForTask;
import com.nag.model.NotiFreqForTaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiFreqForTaskRepository extends JpaRepository<NotiFreqForTask, NotiFreqForTaskId> {

}
