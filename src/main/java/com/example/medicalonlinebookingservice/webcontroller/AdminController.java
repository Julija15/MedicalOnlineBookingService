package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.entity.UserDaten;
import com.example.medicalonlinebookingservice.service.DoctorService;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class AdminController {


   private UserService userService;
   private DoctorService doctorService;

   @GetMapping("/profile/admin")
   public String getUserForAdmin (@PathVariable Long id, Model model,
                                  @AuthenticationPrincipal UserDetails authUser){
   User user = userService.findUserById(id);
   UserDaten userDaten = userService.findUserDatenByUser(user);
   model.addAttribute("user", user);
   model.addAttribute("userDaten",userDaten);
   return "/profile/admin";
   }
}
