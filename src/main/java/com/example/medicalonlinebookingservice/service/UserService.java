package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.dto.UserRegistration;
import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.UserData;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


import static com.example.medicalonlinebookingservice.entity.enums.Role.DOCTOR;
import static com.example.medicalonlinebookingservice.entity.enums.Role.PATIENT;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private VisitService visitService;

    private static final Logger log = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository,VisitService visitService) {
        this.userRepository = userRepository;
        this.visitService = visitService;

    }

    public User loadUserByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        } else {
            throw new IllegalArgumentException("User is not found");
        }
    }

    public User findUser(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.orElse(null);
    }

    public User exist(User user)  {
       User  userDB = loadUserByUsername(user.getUsername());
        {
            if (userDB.getPassword().equals(user.getPassword())) {
                return user;
            } else {
                throw new IllegalArgumentException("User not found");
            }
        }
    }

    public void save(User authUser) {
        userRepository.save(authUser);
        log.info("IN save - user with id: {} save");
    }

    public User findByUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new IllegalArgumentException("User not exists"));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not exist"));
    }

    public User update(User user, User changedUser) {
        if (changedUser != null) {
            user.setEmail(changedUser.getEmail() != null ? changedUser.getEmail() : user.getEmail());
            user.setFirstname(changedUser.getFirstname() != null ? changedUser.getFirstname() : user.getFirstname());
            user.setLastname(changedUser.getLastname() != null ? changedUser.getLastname() : user.getLastname());
            user.setPhoneNumber(changedUser.getPhoneNumber() != null ? changedUser.getPhoneNumber() : user.getPhoneNumber());
            user.setSpecialist(changedUser.getSpecialist() != null ? changedUser.getSpecialist() : user.getSpecialist());
            user.setRole(changedUser.getRole() != null ? changedUser.getRole() : user.getRole());
            log.info("IN update - user with id: {} update ");
        }
        return user;
    }

    public void creatTimeTable(User doctor, LocalDate localDate) {
        List<Visit> visits = new ArrayList<>();
        int hour = 8;
        while (hour <= 18) {
            Visit visit = new Visit(doctor, localDate.atTime(hour, 0), localDate);
           // visits.add(visit);
            hour++;
            visitService.save(visit);
        }
//        List<Visit> allVisits = visitService.findDoctorVisits(doctor);
//        allVisits.addAll(visits);
//        doctor.setVisitList(allVisits);
//        userRepository.save(doctor);
        log.info("IN creatTimeTable - doctor with id: {} and localdate{} creat ");
    }

    public List<User> findAllDoctors() {
        return userRepository.findAllByRole(DOCTOR);
    }

    public User creatUser(UserRegistration userRegistration) {
        User user = new User();
        user.setFirstname(userRegistration.getFirstname());
        user.setLastname(userRegistration.getLastname());
        user.setUsername(userRegistration.getUsername());
        user.setPassword(userRegistration.getPassword());
        user.setPhoneNumber(userRegistration.getPhoneNumber());
        user.setEmail(userRegistration.getEmail());
        user.setGender(userRegistration.getGender());
        user.setDateOfBirth(userRegistration.getDateOfBirth());
        user.setCreatedAt(LocalDate.now());
        user.setRole(PATIENT);
        UserData userData = new UserData();
        userData.setCity(userRegistration.getCity());
        userData.setStreet(userRegistration.getStreet());
        userData.setFlat(userRegistration.getFlat());
        userData.setHouse(userRegistration.getHouse());
        user.setUserData(userData);
        userData.setUser(user);
        log.info("IN creatUser - user with id: {} successfully creat");
        return user;
    }
}

