package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.Doctor;
import com.example.medicalonlinebookingservice.service.DoctorService;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminControllerRest {
    private UserService userService;
    private DoctorService doctorService;
    private VisitService visitService;

    public AdminControllerRest(UserService userService, DoctorService doctorService, VisitService visitService, ResponseEntity<Doctor> updateDoctor) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.visitService = visitService;
    }

    @PostMapping("/admin")
   public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id){

    }
}
