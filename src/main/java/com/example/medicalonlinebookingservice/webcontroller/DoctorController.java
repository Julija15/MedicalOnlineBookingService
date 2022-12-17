package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.DoctorRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public String showAllVisit(User user, DoctorRequest doctorRequest, Model model){
        User doctor = userService.loadUserByUsername(user.getUsername());
        List<Visit> visitList = visitService.findReservedVisits(doctor, doctorRequest.getDate());
        model.addAttribute("visitList",visitList);
        return "/";
    }
}
