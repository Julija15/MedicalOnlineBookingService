package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.entity.enums.Specialist;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/profile/doctor")
public class DoctorController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showAllVisit(@PathVariable LocalDate localDate, long id, Model model){
        User doctor = userService.findUserById(id);
        List<Visit> visitList =userService.findAllVisits(doctor,localDate);
        model.addAttribute("localdate",localDate);
        model.addAttribute("visitList",visitList);
        return "/";
    }
}
