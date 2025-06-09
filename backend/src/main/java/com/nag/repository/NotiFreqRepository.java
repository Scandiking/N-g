/**
 * Repository interface for managing notification frequency settings.
 * This interface extends JpaRepository to provide CRUD operations for NotiFreq entities.
 *
 * @author Kristian
 */

package com.nag.repository;

import com.nag.model.NotiFreq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface NotiFreqRepository extends JpaRepository<NotiFreq, Short> {

    /**
     * Finds a notification frequency by its ID.
     *
     * @param notiFreqId the ID of the notification frequency
     * @return Optional containing the notification frequency if found, otherwise empty
     */
    Optional<NotiFreq> findByNotiFreqId(Short notiFreqId);

    /**
     * Finds notification frequencies by their title.
     *
     * @param notiFreqTitle the title of the notification frequency
     * @return a list of notification frequencies with the specified title
     */
    List<NotiFreq> findByNotiFreqTitle(String notiFreqTitle);

    /**
     * Finds notification frequencies by base interval.
     *
     * @param baseInterval the base interval
     * @return a list of notification frequencies with the specified base interval
     */
    List<NotiFreq> findByBaseInterval(String baseInterval);

    /**
     * Finds notification frequencies by growth factor.
     *
     * @param growthFactor the growth factor
     * @return a list of notification frequencies with the specified growth factor
     */
    List<NotiFreq> findByGrowthFactor(Float growthFactor);

    /**
     * Finds notification frequencies by max repeats.
     *
     * @param maxRepeats the maximum number of repeats
     * @return a list of notification frequencies with the specified max repeats
     */
    List<NotiFreq> findByMaxRepeats(Short maxRepeats);

    /**
     * Checks if a notification frequency exists by its ID.
     *
     * @param notiFreqId the ID of the notification frequency
     * @return true if the notification frequency exists, false otherwise
     */
    boolean existsByNotiFreqId(Short notiFreqId);

    /**
     * Deletes a notification frequency by its ID.
     *
     * @param notiFreqId the ID of the notification frequency to delete
     */
    void deleteByNotiFreqId(Short notiFreqId);
}