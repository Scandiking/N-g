package com.nag.spring_jpa_ng.controller;
import com.nag.spring_jpa_ng.model.Person;
import com.nag.spring_jpa_ng.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins="http://localhost:4321")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    // Get all persons
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Get person by id
    @GetMapping("/persons/phoneNo/{phoneNo}")
    public Person getPersonByPhoneNo(@PathVariable(value = "phoneNo") String phoneNo) {
        return personRepository.findByPhoneNo(phoneNo)
                .orElseThrow(() -> new RuntimeException("Person not found with phone number: " + phoneNo + "\n" +
                        "Please check the phone number and try again."));
    }

    // Get person by first name
    @GetMapping("/persons/name/{firstName}")
    public List<Person> findByFirstName(@PathVariable(value = "firstName") String firstName) {
        List<Person> persons = personRepository.findByFirstName(firstName);
        if (persons.isEmpty()) {
            throw new RuntimeException("Person not found with first name: " + firstName +
                    "\nPlease check the first name and try again.");
        }
        return persons;
    }

    // Get person by last name
    @GetMapping("/persons/last/{lastName}")
    public List<Person> findByLastName(@PathVariable(value = "lastName") String lastName) {
        List<Person> persons = personRepository.findByLastName(lastName);
        if (persons.isEmpty()) {
            throw new RuntimeException("Person not found with last name: " + lastName +
                    "\nPlease check the last name and try again.");
        }
        return persons;
    }

    // Get person by full name
    @GetMapping("/persons/fullname/{firstName}/{lastName}")
    public List<Person> findByFullName(
            @PathVariable(value="firstName") String firstName,
            @PathVariable (value="lastName") String lastName) {
        List<Person> persons = personRepository.findByFirstNameAndLastName(firstName, lastName);
        if (persons.isEmpty()) {
            throw new RuntimeException("Person not found with name: " + firstName + " " + lastName + "\n" +
                    "Please check the name and try again.");
        }
        return persons;
    }

    // Get person by email
    @GetMapping("/persons/email/{email}")
    public List<Person> findByEmail(@PathVariable(value = "email") String email) {
        List<Person> persons = personRepository.findByEmail(email);
        if (persons.isEmpty()) {
            throw new RuntimeException("Person not found with email: " + email +
                    "\nPlease check the email and try again.");
        }
        return persons;
    }

    // Get person by date of birth
    @GetMapping("/persons/dob/{dateOfBirth}")
    public List<Person> findByDateOfBirth(
            @PathVariable(value = "dateOfBirth")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {

        List<Person> persons = personRepository.findByDateOfBirth(dateOfBirth);
        if (persons.isEmpty()) {
            throw new RuntimeException("Person not found with date of birth: " + dateOfBirth +
                    "\nPlease check the date of birth and try again. Make sure the date is in the format yyyy-MM-dd.");
        }
        return persons;
    }




}
