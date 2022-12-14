package com.example.medicalonlinebookingservice.dto;
import com.example.medicalonlinebookingservice.entity.enums.Gender;

import java.time.LocalDate;

public class UserRegistration {

    // добавить роль и специалиста и добавить в изменение юзера

    private String city;

    private String street;

    private String house;

    private String flat;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String name;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
