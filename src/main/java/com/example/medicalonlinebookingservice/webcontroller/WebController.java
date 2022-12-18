package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.dto.UserLogin;
import com.example.medicalonlinebookingservice.dto.UserRegistration;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.enums.Role;
import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/index")
    public String index() {;
        return "index";
    }

    @GetMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute("userRegistration") UserRegistration userRegistration) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String registrationPage(@Valid @ModelAttribute("userRegistration") UserRegistration userRegistration, Model model, HttpSession httpSession) {
        User userDB = userService.findUser(userRegistration.getUsername());
        if (userDB == null) {
            User user = userService.creatUser(userRegistration);
            userService.save(user);
            model.addAttribute("userRegistration",new User());
        } else {
            model.addAttribute("error", "User exist");
            return "/registration";
        }
        return "/login";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, HttpSession httpSession, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "login";
        }
        User userDB = userService.loadUserByUsername(userLogin.getUsername());
        if (userLogin.getPassword().equals(userDB.getPassword())) {
            userDB.setPassword(null);
            httpSession.setAttribute("login",userLogin.getUsername());
            httpSession.setAttribute("user", userDB);
            if (userDB.getRole() == Role.ADMIN) {
//                List<User> doctorList = userService.findAllDoctors();
//                model.addAttribute("doctors", doctorList);
                return "redirect:/profile/admin";
            }
            if (userDB.getRole() == Role.DOCTOR) {
                return "redirect:/profile/doctor";
            }
        } else {
            model.addAttribute("error", "Incorrect username or password");
            return "login";
        }
        return "/profile/patient/patient";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}


