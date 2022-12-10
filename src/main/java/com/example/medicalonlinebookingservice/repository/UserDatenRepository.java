package com.example.medicalonlinebookingservice.repository;

import com.example.medicalonlinebookingservice.entity.UserDaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;


public interface UserDatenRepository extends JpaRepository<UserDaten,Long> {
    UserDaten findUserDatenByUser(User user);
}
