package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit,Long> {

    List<Visit> findAllByDoctorAndDate(User doctor, LocalDate date);

    List<Visit> findAllByPatient(User patient);
}
