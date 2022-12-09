package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedVisitRepository extends JpaRepository<Visit,Long> {
}
