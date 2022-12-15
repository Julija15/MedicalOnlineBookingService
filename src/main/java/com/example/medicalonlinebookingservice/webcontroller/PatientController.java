package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.PatientRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/profile/patient")
public class PatientController {


    private final UserService userService;

    private final VisitService visitService;


    public PatientController(UserService userService,VisitService visitService) {
        this.userService = userService;
        this.visitService = visitService;
    }

    @GetMapping("/")
    public String showAllDoctors(Model model){
        List<User> doctors = userService.findAllDoctors();
        model.addAttribute("doctors",doctors);
        return "/";
    }

    @GetMapping("/")
    public String findVisits(PatientRequest patientRequest, Model model){
        if(patientRequest == null){
            throw new IllegalArgumentException("UserRequest is empty");
        }
        List<Visit> visits = visitService.findFreeVisits(patientRequest.getSpecialist(),patientRequest.getDate());
        model.addAttribute("visits", visits);
        return "/";
    }

    @GetMapping("/")
    public String reservedVisit(User patient, long visitId, Model model){
        visitService.addUserToVisit(patient,visitId);
        model.addAttribute("success", "visit is reserved");
        return "/{id}";
    }

    @GetMapping()
    public String showPatientVisits(User patient, Model model){
        List<Visit> visits = visitService. findAllVisitByPatient(patient);
        model.addAttribute("PatientVisits", visits);
        return "/patient/visits" ;
    }

    @PostMapping()
    public String deletedVisit(User patient,long id){
        User auth = userService.exist(patient);
        visitService.deleteUserFromVisit(auth, id);
        return "/profile/{id}";
    }
}
