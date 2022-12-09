package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}