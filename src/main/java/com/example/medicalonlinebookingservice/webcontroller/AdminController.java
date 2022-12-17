package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;



@Controller
@RequestMapping("/profile/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAdminPage(User authUser, Model model) {
        List<User> doctorList = userService.findAllDoctors();
        model.addAttribute("doctors", doctorList);
        model.addAttribute("authUser",authUser);
        return " /creatTimeTable";
    }
    @PostMapping("/creatTimeTable")
    public String creatTimeTable(@PathVariable Long id, LocalDate localDate, Model model) {
        User doctor = userService.findUserById(id);
        userService.creatTimeTable(doctor, localDate);
        model.addAttribute("doctor", doctor);
        model.addAttribute("localDate",localDate);
        return "/creatTimeTable}";
    }

    @PutMapping("/updateUser")
    public String updateUser(@PathVariable Long id, User user, Model model){
        User userDB = userService.findUserById(id);
        userService.update(userDB,user);
        userService.save(userDB);
        model.addAttribute("firstname",user.getFirstname());
        model.addAttribute("lastname",user.getLastname());
        model.addAttribute("userDB",userDB);
        model.addAttribute("message","User is update");
        return "/user/{id}";
    }
}
