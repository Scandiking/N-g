package com.nag.service;

import com.nag.dto.PersonDTO;
import com.nag.model.Person;
import com.nag.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    // Get all persons
    @Transactional(readOnly = true)
    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    // Get person by phone number
    @Transactional(readOnly = true)
    public PersonDTO getPersonByPhoneNo(String phoneNo) {
        Person person = personRepository.findById(phoneNo)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + phoneNo));
        return toDTO(person);
    }

    // Create a new person
    public PersonDTO createPerson(PersonDTO personDTO) {
        if (personRepository.existsById(personDTO.getPhoneNo())) {
            throw new EntityNotFoundException("Person already exists: " + personDTO.getPhoneNo());
        }
        Person person = toEntity(personDTO);
        Person saved = personRepository.save(person);
        return toDTO(saved);
    }

    // Update an existing person
    public PersonDTO updatePerson(String phoneNo, PersonDTO personDTO) {
        Person person = personRepository.findById(phoneNo)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + phoneNo));

        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setMail(personDTO.getMail());
        person.setProfilePicture(personDTO.getProfilePicture());
        person.setBirthDate(personDTO.getBirthDate());

        Person updated = personRepository.save(person);
        return toDTO(updated);
    }

    // Delete a person
    public void deletePerson(String phoneNo) {
        if (!personRepository.existsById(phoneNo)) {
            throw new EntityNotFoundException("Person not found: " + phoneNo);
        }
        personRepository.deleteById(phoneNo);
    }

    // Additional service methods for relationships

    /**
     * Get count of tasks assigned to a person
     */
    @Transactional(readOnly = true)
    public int getPersonTaskCount(String phoneNo) {
        Person person = personRepository.findById(phoneNo)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + phoneNo));
        return person.getAssignedTaskCount();
    }

    /**
     * Get count of rooms administered by a person
     */
    @Transactional(readOnly = true)
    public int getPersonRoomCount(String phoneNo) {
        Person person = personRepository.findById(phoneNo)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + phoneNo));
        return person.getAdministeredRoomCount();
    }

    /**
     * Check if person is an admin
     */
    @Transactional(readOnly = true)
    public boolean isPersonAdmin(String phoneNo) {
        Person person = personRepository.findById(phoneNo)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + phoneNo));
        return person.isAdmin();
    }

    /**
     * Check if person has assigned tasks
     */
    @Transactional(readOnly = true)
    public boolean personHasTasks(String phoneNo) {
        Person person = personRepository.findById(phoneNo)
                .orElseThrow(() -> new EntityNotFoundException("Person not found: " + phoneNo));
        return person.hasTasks();
    }

    // Mapper methods
    private PersonDTO toDTO(Person person) {
        return new PersonDTO(
                person.getPhoneNo(),
                person.getFirstName(),
                person.getLastName(),
                person.getMail(),
                person.getProfilePicture(),
                person.getBirthDate()
        );
    }

    private Person toEntity(PersonDTO dto) {
        // Use the constructor that matches the new Person entity
        return new Person(
                dto.getPhoneNo(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getMail(),
                dto.getProfilePicture(),
                dto.getBirthDate()
        );
    }
}