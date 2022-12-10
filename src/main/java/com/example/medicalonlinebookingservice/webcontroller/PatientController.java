package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PatientController {

    @Autowired
    private UserService userService;


}
