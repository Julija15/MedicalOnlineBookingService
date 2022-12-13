package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit,Long> {

    List<Visit> findAllByDoctorAndDate(Optional<User> doctor, LocalDate date);

}
