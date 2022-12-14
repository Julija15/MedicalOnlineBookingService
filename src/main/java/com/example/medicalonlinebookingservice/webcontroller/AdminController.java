package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.enums.Role;
import com.example.medicalonlinebookingservice.entity.enums.Specialist;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@Controller
@RequestMapping("/profile")
public class AdminController {

    @Autowired
    private UserService userService;

    private Specialist specialist;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, @AuthenticationPrincipal UserDetails authUser) {
        List<User> doctorList = userService.findAllDoctors();
        model.addAttribute("doctors", doctorList);
        return " /admin";
    }
//как мпередавать localDate
    @PostMapping("admin/doctor/{id}")
    public String creatTimeTable(@PathVariable Long id, LocalDate localDate, Model model) {
        User doctor = userService.findUserById(id);
        userService.creatTimeTable(doctor, localDate);
        model.addAttribute("doctor", doctor);
        model.addAttribute("localDate",localDate);
        return "/admin/doctor/{id}";
    }


    @PutMapping("/admin/user/{id}")
    public String updateUser(@PathVariable Long id, User user, Model model){
        User userDB = userService.findUserById(id);
        userService.update(userDB,user);
        userService.save(userDB);
        return "/admin/user/{id}";
    }
}
