/**
 * RoomController.java
 * Controller for managing room-related operations.
 * This controller provides endpoints to retrieve, create, and delete rooms.
 */

package com.nag.controller;
import com.nag.model.Person;
import com.nag.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Retrieves all persons.
     *
     * @return a list of all persons
     */
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Retrieves a person by their ID.
     *
     * @param id the ID of the person
     * @return the person with the specified ID, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

    /**
     * Creates a new person.
     *
     * @param person the person to create
     * @return the created person with status 201
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    /**
     * Deletes a person by their ID.
     *
     * @param id the ID of the person to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

    /**
     * Updates a person.
     *
     * @param id the ID of the person to update
     * @param person the updated person data
     * @return the updated person, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        if (personRepository.existsById(id)) {
            person.setId(id);
            Person updatedPerson = personRepository.save(person);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

    /**
     * Checks if a person exists by their ID.
     *
     * @param id the ID of the person
     * @return true if the person exists, false otherwise
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> personExists(@PathVariable Long id) {
        return new ResponseEntity<>(personRepository.existsById(id), HttpStatus.OK);
    }
}

    /**
     * Retrieves a person by their email.
     *
     * @param email the email of the person
     * @return the person with the specified email, or 404 if not found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Person> getPersonByEmail(@PathVariable String email) {
        return personRepository.findByEmail(email)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

    /**
     * Retrieves persons by their first name.
     *
     * @param firstName the first name of the persons
     * @return a list of persons with the specified first name
     */
    @GetMapping("/first-name/{firstName}")
    public List<Person> getPersonsByFirstName(@PathVariable String firstName) {
        return personRepository.findByFirstName(firstName);
    }
}

    /**
     * Retrieves persons by their last name.
     *
     * @param lastName the last name of the persons
     * @return a list of persons with the specified last name
     */
    @GetMapping("/last-name/{lastName}")
    public List<Person> getPersonsByLastName(@PathVariable String lastName) {
        return personRepository.findByLastName(lastName);
    }
}

    /**
     * Retrieves persons by their full name.
     *
     * @param firstName the first name of the persons
     * @param lastName the last name of the persons
     * @return a list of persons with the specified full name
     */
    @GetMapping("/full-name/{firstName}/{lastName}")
    public List<Person> getPersonsByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}

    /**
     * Retrieves persons by their phone number.
     *
     * @param phoneNumber the phone number of the persons
     * @return a list of persons with the specified phone number
     */
    @GetMapping("/phone/{phoneNumber}")
    public List<Person> getPersonsByPhoneNumber(@PathVariable String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber);
    }
}