package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAllByRole(String role);

    List<User> findAllBySpecialist(String specialist);



}
