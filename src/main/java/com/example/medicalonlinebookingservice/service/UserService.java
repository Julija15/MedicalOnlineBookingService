package com.example.medicalonlinebookingservice.service;

import com.example.medicalonlinebookingservice.entity.UserDaten;
import com.example.medicalonlinebookingservice.repository.UserDatenRepository;
import com.example.medicalonlinebookingservice.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserDatenRepository userDatenRepository;

    public UserService(UserRepository userRepository, UserDatenRepository userDatenRepository) {
        this.userRepository = userRepository;
        this.userDatenRepository = userDatenRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            return byUsername.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public void save(User authUser, UserDaten userDaten) {
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

    public UserDaten findUserDatenByUser(User user){
        return userDatenRepository.findUserDatenByUser(user);
    }

}
