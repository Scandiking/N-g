package com.nag.repository;

import com.nag.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Person entity.
 * Handles CRUD operations and custom queries for Person data.
 *
 * Primary key is phoneNo (String).
 *
 * @author Team
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    /**
     * Find person by mail address.
     * Used for registration validation and user lookup.
     *
     * @param mail the mail address to search for
     * @return Optional containing Person if found
     */
    Optional<Person> findByMail(String mail);

    /**
     * Find person by first name (case-insensitive).
     *
     * @param firstName the first name to search for
     * @return list of persons with matching first name
     */
    List<Person> findByFirstNameIgnoreCase(String firstName);

    /**
     * Find person by last name (case-insensitive).
     *
     * @param lastName the last name to search for
     * @return list of persons with matching last name
     */
    List<Person> findByLastNameIgnoreCase(String lastName);

    /**
     * Find persons by full name (first + last name, case-insensitive).
     *
     * @param firstName the first name
     * @param lastName the last name
     * @return list of persons matching both names
     */
    List<Person> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    /**
     * Search persons by name containing text (case-insensitive).
     * Searches both first name and last name.
     *
     * @param searchTerm the text to search for
     * @return list of persons whose name contains the search term
     */
    @Query("SELECT p FROM Person p WHERE " +
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Person> findByNameContaining(@Param("searchTerm") String searchTerm);

    /**
     * Check if person exists by mail.
     *
     * @param mail the mail to check
     * @return true if person with mail exists
     */
    boolean existsByMail(String mail);

    /**
     * Find all persons ordered by first name.
     *
     * @return list of all persons sorted by first name
     */
    List<Person> findAllByOrderByFirstNameAsc();

    /**
     * Find all persons ordered by last name.
     *
     * @return list of all persons sorted by last name
     */
    List<Person> findAllByOrderByLastNameAsc();

    /**
     * Custom query to find persons with tasks.
     *
     * @return list of persons who have created tasks
     */
    @Query("SELECT DISTINCT p FROM Person p WHERE p.phoneNo IN " +
            "(SELECT t.creator FROM Task t)")
    List<Person> findPersonsWithTasks();

    /**
     * Count persons by first name.
     *
     * @param firstName the first name to count
     * @return number of persons with the given first name
     */
    long countByFirstNameIgnoreCase(String firstName);
}