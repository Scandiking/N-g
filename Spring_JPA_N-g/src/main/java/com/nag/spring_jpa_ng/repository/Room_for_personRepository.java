package com.nag.spring_jpa_ng.repository;
import java.util.List;
import com.nag.spring_jpa_ng.model.Room_for_person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Room_for_personRepository extends JpaRepository<Room_for_person, Long> {
    <List> Room_for_person findByRoomId(Long roomId);
    <List> Room_for_person findByPersonId(Long personId);
    <List> Room_for_person findByRoomIdAndPersonId(Long roomId, Long personId);
    <List> Room_for_person findByRoomIdAndPersonIdAndRole(Long roomId, Long personId, String role);
    <List> Room_for_person findByRoomIdAndRole(Long roomId, String role);
    <List> Room_for_person findByPersonIdAndRole(Long personId, String role);
    <List> Room_for_person findByRole(String role);
}
