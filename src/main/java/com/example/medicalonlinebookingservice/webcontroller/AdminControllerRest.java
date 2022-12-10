package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.service.DoctorService;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;

import static com.example.medicalonlinebookingservice.entity.enums.Role.DOCTOR;


@RestController
@RequestMapping("/admin")
public class AdminControllerRest {
    private final UserService userService;
    private final DoctorService doctorService;
    private final VisitService visitService;

    private Role role;

    public AdminControllerRest(UserService userService, DoctorService doctorService, VisitService visitService, VisitService visitService1) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.visitService = visitService1;
    }


    @PutMapping("/admin")
   public ResponseEntity<?> editDoctor(@PathVariable Long id,@RequestBody User doctor){
      User user = userService.findUserById(id);
      doctor = userService.addUserRole(user, String.valueOf(DOCTOR));
      doctorService.save(doctor);
      return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<User> updateUser()
}
