package com.nag.spring_jpa_ng.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_frequency")

public class Notification_frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "noti_freq_id")
    private int notiFreqId;

    @Column(name = "noti_freq_title")
    private String notiFreqTitle;

    @Column(name = "base_interval")
    private long baseInterval;

    @Column(name = "growth_factor")
    private long growthFactor;

    @Column(name = "max_repeats")
    private int maxRepeats;

    public Notification_frequency() {

    }

    public Notification_frequency(int notiFreqId, String notiFreqTitle, long baseInterval, long growthFactor, int maxRepeats) {
        this.notiFreqId = notiFreqId;
        this.notiFreqTitle = notiFreqTitle;
        this.baseInterval = baseInterval;
        this.growthFactor = growthFactor;
        this.maxRepeats = maxRepeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getNotiFreqId() {
        return notiFreqId;
    }
    public void setNotiFreqId(int notiFreqId) {
        this.notiFreqId = notiFreqId;
    }
    public String getNotiFreqTitle() {
        return notiFreqTitle;
    }
    public void setNotiFreqTitle(String notiFreqTitle) {
        this.notiFreqTitle = notiFreqTitle;
    }
    public long getBaseInterval() {
        return baseInterval;
    }
    public void setBaseInterval(long baseInterval) {
        this.baseInterval = baseInterval;
    }
    public long getGrowthFactor() {
        return growthFactor;
    }
    public void setGrowthFactor(long growthFactor) {
        this.growthFactor = growthFactor;
    }
    public int getMaxRepeats() {
        return maxRepeats;
    }
    public void setMaxRepeats(int maxRepeats) {
        this.maxRepeats = maxRepeats;
    }

    @Override
    public String toString() {
        return "Notification_frequency [id=" + id + ", notiFreqId=" + notiFreqId + ", notiFreqTitle=" + notiFreqTitle + ", baseInterval=" + baseInterval + ", growthFactor=" + growthFactor + ", maxRepeats=" + maxRepeats + "]";
    }
}