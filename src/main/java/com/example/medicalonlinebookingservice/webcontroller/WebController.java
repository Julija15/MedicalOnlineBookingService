package com.example.medicalonlinebookingservice.webcontroller;


import com.example.medicalonlinebookingservice.dto.UserRegistration;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.UserData;
import com.example.medicalonlinebookingservice.entity.enums.Role;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private UserService userService;


    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(@Valid @ModelAttribute("authUser") UserDetails authUser, Model model) {
        return "index";
    }

    @GetMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute("authUser") UserDetails authUser) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute("userRegistration") UserRegistration userRegistration, Model model, HttpSession httpSession) {
        User userDB = userService.loadUserByUsername(userRegistration.getUsername());
        if (userDB == null) {
            User user = userService.creatUser(userRegistration);
            userService.save(user);
        } else {
            model.addAttribute("error", "User exist");
            return "redirect: /registration";
        }
        return "redirect: /login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("authUser") UserDetails authUser, BindingResult bindingResult,
                        HttpSession httpSession, Model model) {
        User userDB = userService.loadUserByUsername(authUser.getUsername());
        if (userDB.getPassword().equals(new BCryptPasswordEncoder(12).encode(authUser.getPassword()))) {
            httpSession.setAttribute("user", userDB);
            if (userDB.getRole() == Role.ADMIN) {
                List<User> doctorList = userService.findAllDoctors();
                model.addAttribute("doctors", doctorList);
                return " /admin";
            }
            if (userDB.getRole() == Role.DOCTOR) {
            }
        } else {
            model.addAttribute("error", "Incorrect username or password");
            return "login";
        }
        return "login";
    }

//    @GetMapping("/profile")
//    public String UserPage(@AuthenticationPrincipal UserDetails authUser, Model model) {
//        User user = userService.findByUser(authUser.getUsername());
//        UserDetails currentUserDaten = (UserDetails) userService.findUserDataByUser(user);
//        model.addAttribute("currentUserDaten", currentUserDaten);
//        model.addAttribute("user", user);
//        if (user.getRole() == Role.ADMIN) {
//            return "redirect: /profile/admin";
//        }
//        if (user.getRole() == Role.DOCTOR) {
//            return "redirect: /profile/doctor";
//        }
//        return "profile";
//    }
}


