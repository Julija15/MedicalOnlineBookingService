package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.entity.enums.Specialist;
import com.example.medicalonlinebookingservice.repository.UserRepository;
import com.example.medicalonlinebookingservice.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitService {
    @Autowired
    private UserService userService;

    private VisitRepository visitRepository;
    private UserRepository userRepository;

    public VisitService(VisitRepository visitRepository, UserRepository userRepository, UserService userService) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public List<Visit> findFreeVisits(Specialist specialist, LocalDate date) {
        if (specialist == null || date == null) {
            throw new IllegalArgumentException("Bad Request, specialist or date is empty");
        }
        List<User> doctors = userRepository.findAllBySpecialist(specialist.name());
        List<Visit> visits = new ArrayList<>();
        for (User doctor : doctors) {
            List<Visit> doctorVisits = visitRepository.findAllByDoctorAndDate(doctor, date).stream().filter(Visit::isNotReserved).collect(Collectors.toList());
            visits.addAll(doctorVisits);
        }
        return visits;
    }


    public Optional<Visit> findById(long id) {
        return visitRepository.findById(id);
    }

    public void addUserToVisit(User patient, long visitId) {
        Optional<Visit> visitDB = visitRepository.findById(visitId);
        if (visitDB.isPresent()) {
            Visit visit = visitDB.get();
            if (visit.isNotReserved()) {
                visit.setPatient(patient);
                patient.getVisitList().add(visit);
                userRepository.save(patient);
            } else {
                throw new IllegalArgumentException("Visit is reserved");
            }
        } else {
            throw new IllegalArgumentException("Visit is not found");
        }
    }

    public void deleteUserFromVisit(User patient, long visitId) {
        Optional<Visit> visitDB = visitRepository.findById(visitId);
        if (visitDB.isPresent()) {
            Visit visit = visitDB.get();
            if (visit.getPatient() != null && visit.getPatient().getId() == patient.getId()) {
                visit.setPatient(null);
                visitRepository.save(visit);
            } else {
                throw new IllegalArgumentException("Visit is reserved");
            }
        } else {
            throw new IllegalArgumentException("Visit is not found");
        }
    }

    public List<Visit> findReservedVisits(User doctor, LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Bad Request, date is empty");
        }
        List<Visit> visits = visitRepository.findAllByDoctorAndDate(doctor, date);
        return visits.stream().filter(Visit::isReserved).collect(Collectors.toList());
    }
}
