package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.Dto.Timetable;
import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @GetMapping
    public String getAll(Model model){
       List<Doctor> doctorList = doctorService.findAll();
       model.addAttribute("doctors",doctorList);
       return "/doctor";
    }


    public String createVisites(Timetable timetable, Model model){


    }
}
