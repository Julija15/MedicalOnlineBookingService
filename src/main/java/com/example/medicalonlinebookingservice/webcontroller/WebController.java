package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.UserDaten;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class WebController {

    private UserService userService;

    private UserDaten userDaten;

    @GetMapping
    public String index(@Valid @ModelAttribute("authUser") UserDetails authUser, Model model) {
        return "index";
    }

    @GetMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute("authUser") UserDetails authUser) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute("authUser") UserDetails authUser, BindingResult bindingResult, HttpSession httpSession) {
        UserDetails currentUserDaten = userService.loadUserByUsername(authUser.getUsername());
        if (currentUserDaten.isAccountNonExpired()) {
            userService.save((User) authUser,userDaten);
            httpSession.setAttribute("currentUserDaten", currentUserDaten);
        }
        return "redirect: /login";
    }

    @GetMapping("/login")
    public String login(@Valid @ModelAttribute("authUser") UserDetails authUser, BindingResult bindingResult,
                        HttpSession httpSession, Model model) {
        UserDetails currentUserPassword = userService.loadUserByUsername(authUser.getPassword());
        if (currentUserPassword.getPassword().equals(authUser.getPassword())) {
            httpSession.setAttribute("currentUserPassword", currentUserPassword);
            return "profile";
        } else {
            return "registration";
        }
    }
        @GetMapping("/profile")
        public String UserPage(@AuthenticationPrincipal UserDetails authUser, Model model){
            User user = userService.findByUser(authUser.getUsername(user));
            UserDetails currentUserDaten = userService.loadUserByUsername(authUser.getUsername());
            model.addAttribute("currentUserDaten",currentUserDaten);
            model.addAttribute("user", user);
            return "profile";
        }
    }

