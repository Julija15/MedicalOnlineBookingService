package com.example.medicalonlinebookingservice.dto;

import com.example.medicalonlinebookingservice.entity.User;

import java.time.LocalDate;

public class DoctorRequest {

    private User doctor;

    private LocalDate date;

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
