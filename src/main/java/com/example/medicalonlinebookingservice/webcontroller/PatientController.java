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
@RequestMapping("/profile")
public class PatientController {


    private final UserService userService;

    private final VisitService visitService;


    public PatientController(UserService userService,VisitService visitService) {
        this.userService = userService;
        this.visitService = visitService;
    }

    @GetMapping()
    public String showAllDoctor(User doctor, Model model){
        List<User> doctors = userService.findAllDoctors();
        model.addAttribute("doctors",doctors);
        return "/";
    }

    @GetMapping()
    public String showAllDoctorVisit(PatientRequest patientRequest, Model model){
        if(patientRequest == null){
            throw new IllegalArgumentException("UserRequest is empty");
        }
        List<Visit> visits = visitService.findFreeVisits(patientRequest.getSpecialist(),patientRequest.getDate());
        model.addAttribute("visits", visits);
        return "/";
    }

    @PostMapping()
    public String reservedVisit(@AuthenticationPrincipal UserDetails userDetails, long id){
        User patient = userService.exist(userDetails);
        visitService.addUserToVisit(patient,id);
        return "/profile/{id}";
    }

    @PostMapping()
    public String deletedVisit(@AuthenticationPrincipal UserDetails userDetails,long id){
        User auth = userService.exist(userDetails);
        visitService.deleteUserFromVisit(auth, id);
        return "/profile/{id}";
    }
}
