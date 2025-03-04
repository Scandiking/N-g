package com.nag.spring_jpa_ng.repository;

import java.util.List;


import com.nag.spring_jpa_ng.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
    <List> Person findByFirstName(String firstName);
    <List> Person findByLastName(String lastName);
    <List> Person findByEmail(String email);
    <List> Person findByPhoneNo(String phoneNo);
}
