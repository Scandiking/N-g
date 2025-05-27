package com.java.nag.repository;
import com.java.nag.model.NotiFreq;
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
public interface ProductRepository extends JpaRepository<NotiFreq, Short>
{
    /**
     * Finds all notification frequencies by user ID.
     *
     * @param userId the ID of the user
     * @return a list of notification frequencies for the specified user
     */
    List<NotiFreq> findByUserId(String userId);

    /**
     * Finds a notification frequency by its ID.
     *
     * @param notiFreqId the ID of the notification frequency
     * @return the notification frequency with the specified ID, or null if not found
     */
    NotiFreq findByNotiFreqId(Short notiFreqId);
}

    /**
     * Deletes a notification frequency by its ID.
     *
     * @param notiFreqId the ID of the notification frequency to delete
     */
    void deleteByNotiFreqId(Short notiFreqId);
}

    /**
     * Saves a notification frequency.
     *
     * @param notiFreq the notification frequency to save
     * @return the saved notification frequency
     */
    NotiFreq save(NotiFreq notiFreq);
}

    /**
     * Updates a notification frequency.
     *
     * @param notiFreq the notification frequency to update
     * @return the updated notification frequency
     */
    NotiFreq update(NotiFreq notiFreq);
}

    /**
     * Checks if a notification frequency exists by its ID.
     *
     * @param notiFreqId the ID of the notification frequency
     * @return true if the notification frequency exists, false otherwise
     */
    boolean existsByNotiFreqId(Short notiFreqId);
}

    /**
     * Finds all notification frequencies.
     *
     * @return a list of all notification frequencies
     */
    List<NotiFreq> findAll();
}

    /**
     * Finds notification frequencies by their title.
     *
     * @param title the title of the notification frequency
     * @return a list of notification frequencies with the specified title
     */
    List<NotiFreq> findByNotiFreqTitle(String title);
}