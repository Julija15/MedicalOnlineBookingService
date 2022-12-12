package com.example.medicalonlinebookingservice.webcontroller;


import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.UserData;
import com.example.medicalonlinebookingservice.entity.enums.Role;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String registrationPage(@Valid @ModelAttribute("authUser") User authUser, BindingResult bindingResult, HttpSession httpSession) {
        User currentUserDaten = userService.loadUserByUsername(authUser.getUsername());
        if (currentUserDaten == null) {
            UserData userData = new UserData();
            authUser.setUserDaten(userData);
            userService.save(authUser);
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
    public String UserPage(@AuthenticationPrincipal UserDetails authUser, Model model) {
        User user = userService.findByUser(authUser.getUsername());
        UserDetails currentUserDaten = (UserDetails) userService.findUserDataByUser(user);
        model.addAttribute("currentUserDaten", currentUserDaten);
        model.addAttribute("user", user);
        if (user.getRole() == Role.ADMIN) {
            return "redirect: /profile/admin";
        }
        if (user.getRole() == Role.DOCTOR) {
            return "redirect: /profile/doctor";
        }
        return "profile";
    }
}


