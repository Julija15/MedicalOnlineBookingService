package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public void creatTimeTable(Doctor doctor, LocalDate localDate){
        List<Visit> visits = new ArrayList<>();
        int hour = 8;
        while (hour <= 18)
        {
            Visit visit = new Visit(doctor, localDate.atTime(hour, 0));
            visits.add(visit);
            hour++;
        }
        doctor.setVisitList(visits);
        doctorRepository.save(doctor);
    }

    public Optional<Doctor> fidById(Long id) {
        return doctorRepository.findById(id);
    }
}
