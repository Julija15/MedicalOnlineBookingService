package com.example.medicalonlinebookingservice.dto;

import java.time.LocalDate;

public class DoctorRequest {

    private Long doctorId;

    private LocalDate visitsDate;

    public DoctorRequest() {
    }

    public DoctorRequest(Long doctorId, LocalDate visitsDate) {
        this.doctorId = doctorId;
        this.visitsDate = visitsDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getVisitsDate() {
        return visitsDate;
    }

    public void setVisitsDate(LocalDate visitsDate) {
        this.visitsDate = visitsDate;
    }
}
