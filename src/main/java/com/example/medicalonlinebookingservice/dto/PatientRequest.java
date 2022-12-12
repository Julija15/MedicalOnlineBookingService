package com.example.medicalonlinebookingservice.dto;

import com.example.medicalonlinebookingservice.entity.enums.Specialist;

import java.time.LocalDate;

public class PatientRequest {

    private Specialist specialist;

    private LocalDate date;

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
