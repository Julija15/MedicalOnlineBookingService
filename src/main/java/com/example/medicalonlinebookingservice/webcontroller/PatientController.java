package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.PatientRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/{id}")
    public String showAllDoctors(Model model){
        List<User> doctors = userService.findAllDoctors();
        model.addAttribute("doctors",doctors);
        return "redirect: /{id}/doctor/visits";
    }

    @GetMapping("/{id}/doctor/visits")
    public String findVisits(PatientRequest patientRequest, Model model){
        if(patientRequest == null){
            throw new IllegalArgumentException("UserRequest is empty");
        }
        List<Visit> visits = visitService.findFreeVisits(patientRequest.getSpecialist(),patientRequest.getDate());
        model.addAttribute("visits", visits);
        return "redirect:/{id}/doctor/visits/reserved";
    }

    @GetMapping("/{id}/doctor/visits/reserved")
    public String reservedVisit(User patient, long visitId, HttpSession session, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "/login";
        }
        patient = userService.exist(patient);
        session.setAttribute("patient", patient);
        visitService.addUserToVisit(patient,visitId);
        model.addAttribute("success", "visit is reserved");
        return "redirect:/{id}/visitsPatient";
    }

    @GetMapping("/{id}/patientVisits")
    public String showPatientVisits(User patient, Model model){
        List<Visit> visits = visitService. findAllVisitByPatient(patient);
        model.addAttribute("PatientVisits", visits);
        return "redirect:/patient/{id}/patientVisits/deletedVisits" ;
    }

    @PostMapping("/{id}/deletedVisits")
    public String deletedVisit(User patient,long id,HttpSession session, BindingResult bindingResult,Model model){
        User auth = userService.exist(patient);
        visitService.deleteUserFromVisit(auth, id);
        session.setAttribute("patient",auth);
        return "/profile/{id}";
    }
}
