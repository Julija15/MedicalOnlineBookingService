package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.PatientRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


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
    public String reservedVisit(long id, User auth){
        Optional<Visit> visit = visitService.findById(id);
        if(visit.isPresent()){
            if (visit.get().isNotReserved()){
                Visit reservedvisit = visitService.addUserToVisit(auth,id);
            }else{
                throw new IllegalArgumentException("Visit is reserved");
            }
        }
        return "/profile/{id}";
    }

    @PostMapping()
    public String deletedVisit(long id, User auth){
        Optional<Visit> visit = visitService.findById(id);
        if(visit.isPresent()){
            if (visit.get().isNotReserved()){
                throw new IllegalArgumentException("Visit is not reserved");

            }else{
                Visit deleteVisit = visitService.deleteUserFromVisit(id, auth);
            }
        }
        return "/profile/{id}";
    }
}
