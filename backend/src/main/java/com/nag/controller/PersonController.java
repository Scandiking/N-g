/**
 * PersonController.java
 * Controller for managing person-related operations.
 * @description This controller provides endpoints to retrieve, create, update, and delete persons.
 * @author Kristian
 */

package com.nag.controller;

import com.nag.dto.*;
import com.nag.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nag.model.Person;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person Management", description = "Operations related to managing persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    @Operation(summary = "Get all persons", description = "Retrieve a list of all persons")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of persons"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{phoneNo}")
    @Operation(summary = "Get person by phone number", description = "Retrieve a person by their phone number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved person"),
        @ApiResponse(responseCode = "404", description = "Person not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PersonDTO> getPersonByPhoneNo(@PathVariable String phoneNo) {
        PersonDTO person = personService.getPersonByPhoneNo(phoneNo);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new person", description = "Create a new person with the provided details")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created person"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        PersonDTO createdPerson = personService.createPerson(personDTO);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{phoneNo}")
    @Operation(summary = "Update an existing person", description = "Update the details of an existing person")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully updated person"),
        @ApiResponse(responseCode = "404", description = "Person not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable String phoneNo, @Valid @RequestBody PersonDTO personDTO) {
        PersonDTO updatedPerson = personService.updatePerson(phoneNo, personDTO);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{phoneNo}")
    @Operation(summary = "Delete a person", description = "Delete a person by their phone number")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Successfully deleted person"),
        @ApiResponse(responseCode = "404", description = "Person not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deletePerson(@PathVariable String phoneNo) {
        personService.deletePerson(phoneNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}