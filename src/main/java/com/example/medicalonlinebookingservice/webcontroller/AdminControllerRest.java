package com.example.medicalonlinebookingservice.webcontroller;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.service.DoctorService;
import com.example.medicalonlinebookingservice.service.UserService;
import com.example.medicalonlinebookingservice.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public AdminControllerRest(UserService userService, DoctorService doctorService, VisitService visitService, VisitService visitService1) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.visitService = visitService1;
    }


    @PutMapping("/admin")
   public ResponseEntity<?> editUser(@PathVariable Long id,@RequestBody User user){
      User userDB = userService.findUserById(id);
      userDB.setRole(user.getRole());
      userService.save(user);
      return new ResponseEntity<>(HttpStatus.OK);
    }
}
