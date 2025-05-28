package com.java.nag.repository;
import com.java.nag.model.NotiFreq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Task entity.
 * This interface extends JpaRepository to provide CRUD operations for NotiFreq entities.
 *
 * @author Lucas
 */

@Repository
public interface ProductRepository extends JpaRepository<Task, Integer>{

    /**
     * Finds all tasks that has not yet reched their deadline
     *
     * @param dueDateTime from the user
     * @return all tasks that has a due-date later than input
     */
    List<Task> findByDueDateTimeAfter(LocalDateTime tidspunkt);

    /**
     * Finds all tasks that are not completed
     *
     * @param checks if boolean value "completed" is false
     * @return a list of tasks that is yet to be completed
     */
    List<Task> findByCompletedFalse();
}