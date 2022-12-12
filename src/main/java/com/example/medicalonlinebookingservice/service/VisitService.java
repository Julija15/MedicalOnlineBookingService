package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.entity.enums.Specialist;
import com.example.medicalonlinebookingservice.repository.UserRepository;
import com.example.medicalonlinebookingservice.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {

    private VisitRepository visitRepository;

    private UserRepository userRepository;



    public List<Visit> findVisits(Specialist specialist, LocalDate date){
        if(specialist == null || date == null){
            throw new IllegalArgumentException("Bad Request, specialist or date is empty");
        }
       List<User> doctors = userRepository.findAllBySpecialist(specialist.name());
       List<Visit> visits = new ArrayList<>();
        for (User doctor : doctors) {
            List<Visit> doctorVisits = visitRepository.findAllByDoctorAndDate(doctor,date);
            visits.addAll(doctorVisits);
        }
        return visits;
    }
}
