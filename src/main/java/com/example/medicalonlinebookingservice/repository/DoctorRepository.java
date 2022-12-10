package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findAll(Doctor doctor);
}