package com.example.medicalonlinebookingservice.dto;
import com.example.medicalonlinebookingservice.entity.enums.Gender;
import com.example.medicalonlinebookingservice.entity.enums.Role;
import com.example.medicalonlinebookingservice.entity.enums.Specialist;

import java.time.LocalDate;

public class UserRegistration {

    private Role role;

    private Specialist specialist;

    private String city;

    private String street;

    private String house;

    private String flat;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    public UserRegistration() {
    }

    public UserRegistration(Role role, Specialist specialist, String city, String street, String house, String flat, LocalDate dateOfBirth, Gender gender, String firstname, String lastname, String username, String password, String email, String phoneNumber) {
        this.role = role;
        this.specialist = specialist;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
