package com.nag.spring_jpa_ng.repository;

import com.nag.spring_jpa_ng.model.Notification_frequency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Notification_frequencyRepository extends JpaRepository<Notification_frequency, Long> {
    List <Notification_frequency> findByNotiFreq(String notiFreq);
    List <Notification_frequency> findByNotiFreqId(int notiFreqId);
    List <Notification_frequency> findByNotiFreqTitle(String notiFreqTitle);
    List <Notification_frequency> findByBaseInterval(double baseInterval);
    List <Notification_frequency> findByGrowthFactor(double growthFactor);
    List <Notification_frequency> findByMaxRepeats(int maxRepeats);
}
