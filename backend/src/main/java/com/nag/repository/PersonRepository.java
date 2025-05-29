package com.nag.repository;

import com.nag.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {  // Endret fra Long til String
    Optional<Person> findByMail(String mail);
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    // Fjernet findByPhoneNo siden findById() nå gjør samme jobb
}