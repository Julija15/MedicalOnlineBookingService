package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.repository.DoctorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

 private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Optional<Doctor> findAll(Doctor doctor) {
        return doctorRepository.findAll(doctor);
    }

    public void save(User user) {
        doctorRepository.save(user);
    }
}
