package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.DoctorRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profile/doctor")
public class DoctorController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private UserService userService;

    public DoctorController(VisitService visitService,UserService userService) {
        this.visitService = visitService;
        this.userService = userService;
    }

    @GetMapping
    public String getDoctorPage(HttpSession session, Model model){
        Object login = session.getAttribute("login");
        if (login != null) {
            User doctor = userService.loadUserByUsername((String) login);
            session.setAttribute("doctor", doctor);
            List<Visit> visitList = visitService.findReservedVisits(doctor);
            model.addAttribute("visitList",visitList);
            return "/profile/doctor/doctor";
        }
        return "/login";
    }

//    @GetMapping("/visits")
//    public String showAllVisit(HttpSession session, Model model){
//        Object login = session.getAttribute("login");
//        if (login != null) {
//            User doctor = userService.loadUserByUsername((String) login);
//            session.setAttribute("doctor", doctor);
//            List<Visit> visitList = visitService.findReservedVisits(doctor);
//            visitService.addUserToVisit(patient, visit.getId());
//            model.addAttribute("visitList",visitList);
//            model.addAttribute("success", "visit is reserved");
//            return "/profile/patient/patient";
//        }
//        return "/login";
//    }

}
