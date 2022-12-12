package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.PatientRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class PatientController {


    private UserService userService;

    private VisitService visitService;


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
        List<Visit> visits = visitService.findVisits(patientRequest.getSpecialist(),patientRequest.getDate());
        model.addAttribute("visits", visits);
        return "/";
    }

//    @PostMapping()
//    public String reservedVisit(LocalDate localDate, User user,Model model){
//
//    }
}
