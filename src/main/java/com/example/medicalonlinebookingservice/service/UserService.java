package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.User;
import com.example.medicalonlinebookingservice.entity.UserData;
import com.example.medicalonlinebookingservice.entity.Visit;
import com.example.medicalonlinebookingservice.repository.UserDataRepository;
import com.example.medicalonlinebookingservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.medicalonlinebookingservice.entity.enums.Role.DOCTOR;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserDataRepository userDataRepository;
    private User user;

    public UserService(UserRepository userRepository, UserDataRepository userDataRepository) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return byUsername.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public void save(User authUser) {
    userRepository.save(authUser);
    }

    public User findByUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new IllegalArgumentException("User not exists"));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not exist"));
    }

    public UserData findUserDataByUser(User user){
        return userDataRepository.findByUser(user);
    }

    public User update(User user, User changedUser) {
        if (changedUser != null) {
            user.setEmail(changedUser.getEmail() != null ? changedUser.getEmail() : user.getEmail());
            user.setName(changedUser.getName() != null ? changedUser.getName() : user.getName());
            user.setPhoneNumber(changedUser.getPhoneNumber() != null ? changedUser.getPhoneNumber() : user.getPhoneNumber());
            user.setSpecialist(changedUser.getSpecialist() != null ? changedUser.getSpecialist() : user.getSpecialist());
            user.setSpecialist(changedUser.getSpecialist() != null ? changedUser.getSpecialist() : user.getSpecialist());
        }
      return user;
    }

    public void creatTimeTable(User doctor, LocalDate localDate){
        List<Visit> visits = new ArrayList<>();
        int hour = 8;
        while (hour <= 18)
        {
            Visit visit = new Visit(doctor, localDate.atTime(hour, 0), localDate);
            visits.add(visit);
            hour++;
        }
        doctor.setVisitList(visits);
        userRepository.save(doctor);
    }

    public List<User> findAllDoctors() {
        return userRepository.findAllByRole(DOCTOR.name());
    }

    public List<Visit> findAllVisits(User doctor, LocalDate localDate) {
       Optional<User> doctor = userRepository.findById(user.getId());
      List<Visit> visits = 
      return visits;
      
    }
}

