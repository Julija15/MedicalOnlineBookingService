package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.repository.DoctorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
