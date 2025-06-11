package com.nag.repository;

import com.nag.model.NotiFreq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing notification frequency settings.
 * This interface extends JpaRepository to provide CRUD operations for NotiFreq entities.
 *
 * @author Kristian
 */
@Repository
public interface NotiFreqRepository extends JpaRepository<NotiFreq, Short> {

    /**
     * Finds notification frequencies by their title.
     * Useful for searching or filtering by human-readable names.
     *
     * @param notiFreqTitle the title of the notification frequency
     * @return a list of notification frequencies with the specified title
     */
    List<NotiFreq> findByNotiFreqTitle(String notiFreqTitle);

    /**
     * Finds notification frequencies by base interval.
     * Useful for grouping similar frequency patterns.
     *
     * @param baseInterval the base interval
     * @return a list of notification frequencies with the specified base interval
     */
    List<NotiFreq> findByBaseInterval(String baseInterval);

    // Valgfritt - avhenger av om dere faktisk bruker disse:

    /**
     * Finds notification frequencies by growth factor.
     * @param growthFactor the growth factor
     * @return a list of notification frequencies with the specified growth factor
     */
    List<NotiFreq> findByGrowthFactor(float growthFactor); // Fikset: float i stedet for Float

    /**
     * Finds notification frequencies by max repeats.
     * @param maxRepeats the maximum number of repeats
     * @return a list of notification frequencies with the specified max repeats
     */
    List<NotiFreq> findByMaxRepeats(Short maxRepeats);
}