package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.PatientRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/profile/patient")
public class PatientController {


    private final UserService userService;

    private final VisitService visitService;


    public PatientController(UserService userService,VisitService visitService) {
        this.userService = userService;
        this.visitService = visitService;
    }

    @GetMapping
    public String getPatientPage(HttpSession session, Model model){
        return "/profile/patient/patient";
    }

    @PostMapping("/visits")
    public String findVisits(@ModelAttribute("patientRequest") PatientRequest patientRequest, HttpSession session, Model model){
        if(patientRequest == null){
            throw new IllegalArgumentException("UserRequest is empty");
        }
        List<Visit> visits = visitService.findFreeVisits(patientRequest.getSpecialist(),patientRequest.getDate());
        model.addAttribute("visits", visits);
        return "/profile/patient/visits";
    }

    @PostMapping("reserveVisit")
    public String reservedVisit(@ModelAttribute("visit") Visit visit, HttpSession session, Model model){
        Object login = session.getAttribute("login");
        if (login != null) {
            User patient = userService.loadUserByUsername((String) login);
            session.setAttribute("patient", patient);
            visitService.addUserToVisit(patient, visit.getId());
            model.addAttribute("success", "visit is reserved");
            return "/profile/patient/patient";
        }
        return "/login";
    }

    @GetMapping("/patientVisits")
    public String showPatientVisits(HttpSession session, Model model){
        Object login = session.getAttribute("login");
        if (login != null) {
            User patient = userService.loadUserByUsername((String) login);
            List<Visit> visits = visitService.findAllVisitByPatient(patient);
            model.addAttribute("patientVisits", visits);
            return "/profile/patient/patientVisits" ;
        }
        return "/login";
    }

    @PostMapping("/deleteVisit")
    public String deletedVisit(@ModelAttribute("visit") Visit visit, HttpSession session, Model model){
        Object login = session.getAttribute("login");
        if (login != null) {
            User patient = userService.loadUserByUsername((String) login);
            visitService.deleteUserFromVisit(patient, visit.getId());
            session.setAttribute("patient",patient);
            return "/profile/patient/patient";
        }
        return "/login";
    }
}
