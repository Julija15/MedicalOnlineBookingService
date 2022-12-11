package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.service.DoctorService;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

   @Autowired
   private UserService userService;
   @Autowired
   private DoctorService doctorService;

   @GetMapping("/profile/admin")
   public String getAdminPage ( Model model,@AuthenticationPrincipal UserDetails authUser){
   List<Doctor> doctorList = doctorService.findAll();
   model.addAttribute("doctors",doctorList);
   return "/profile/admin";
   }

   @GetMapping("/doctor/{id}")
   @PostMapping
   public String creatTimeTable(@PathVariable Long id, LocalDate localDate, Model model){
      Optional<Doctor> doctor = doctorService.fidById(id);
      if(doctor.isPresent()){
         doctorService.creatTimeTable(doctor.get(),localDate);
      }
   }
}
