package com.nag.spring_jpa_ng.repository;

import com.nag.spring_jpa_ng.model.Notification_frequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Notification_frequencyRepository extends JpaRepository<Notification_frequency, Long> {
    <List> Notification_frequency findByNotiFreq(String notiFreq);
    <List> Notification_frequency findByNotiFreqId(Long notiFreqId);
}
