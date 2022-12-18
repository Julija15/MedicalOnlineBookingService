package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.DoctorRequest;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
@RequestMapping("/profile/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPage(HttpSession session, Model model) {
        List<User> doctorList = userService.findAllDoctors();
        model.addAttribute("doctors", doctorList);
        return "/profile/admin/admin";
    }
    @PostMapping("/createTimeTable")
    public String creatTimeTable(@ModelAttribute ("doctorRequest") DoctorRequest doctorRequest, Model model, HttpSession session) {
        User doctor = userService.findUserById(doctorRequest.getDoctorId());
        userService.creatTimeTable(doctor, doctorRequest.getVisitsDate());
        model.addAttribute("doctor", doctor);
        model.addAttribute("date",doctorRequest.getVisitsDate());
        return "/profile/admin/admin";
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
