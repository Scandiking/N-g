/**
 * @description Entity class representing notification frequency settings.
 * This class is used to store the frequency of notifications for a user.
 * It includes fields for the ID, user ID, and frequency type.
 * @author Kristian
 */

package com.nag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "noti_freq")
@Setter
@Getter
public class NotiFreq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short notiFreqId; /// korresponderer med SMALLINT i SQL

    @Column(nullable = false)
    private String notiFreqTitle; // korresponderer med TEXT i SQL

    @Column(nullable = false)
    private String baseInterval; // Interval is not directly supported so use String or Duration

    @Column(nullable = false)
    private float growthFactor; // korresponderer med REAL i SQL

    @Column(nullable = false)
    private short maxRepeats; // short korresponderer med SMALLINT i SQL

    // Add custom fields and RELATIONSHIPS here (@manyToOne, @OneToMany, etc.)


}