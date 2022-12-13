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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private UserRepository userRepository;

    public VisitService(VisitRepository visitRepository, UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
    }


    public List<Visit> findFreeVisits(Specialist specialist, LocalDate date){
        if(specialist == null || date == null){
            throw new IllegalArgumentException("Bad Request, specialist or date is empty");
        }
       List<Optional<User>> doctors = userRepository.findAllBySpecialist(specialist.name());
       List<Visit> visits = new ArrayList<>();
        for (Optional<User> doctor : doctors) {
            List<Visit> doctorVisits = visitRepository.findAllByDoctorAndDate(doctor,date).stream().filter(Visit::isNotReserved).collect(Collectors.toList());
            visits.addAll(doctorVisits);
        }
        return visits;
    }


    public Optional<Visit> findById(long id) {
        return visitRepository.findById(id);
    }

    public Visit addUserToVisit(User patient, long id ) {
       Optional<Visit> visit = visitRepository.findById(id);
     return visitRepository.addUserToVisit(patient,id);
    }

    public Visit deleteUserFromVisit(long id,User patient) {
        Optional<Visit> visit = visitRepository.findById(id);
        return visitRepository.deleteUserFromVisit(patient, id);
    }

    public List<Visit> findVisitsByDoctor(Long id, LocalDate date) {
        if( date == null){
            throw new IllegalArgumentException("Bad Request, date is empty");
        }
        Optional<User> doctor = userRepository.findById(id);
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : visits) {
            List<Visit> doctorVisits = visitRepository.findAllByDoctorAndDate(doctor,date).stream().filter(Visit::isNotReserved).collect(Collectors.toList());
            visits.addAll(doctorVisits);
        }
        return visits;
    }
}
