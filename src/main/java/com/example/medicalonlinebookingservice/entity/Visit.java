package com.example.medicalonlinebookingservice.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Table(name = "visits")
public class Visit extends AbstractEntity {

    @OneToOne
    public User doctor;

    @ManyToOne
    public User patient;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private LocalDateTime startTime;

    private LocalDate date;



    public Visit(User doctor, LocalDateTime startTime,LocalDate date) {
        this.doctor = doctor;
        this.startTime = startTime;
        this.date = date;
        this.createdAt = LocalDateTime.now();

    }

    public Visit() {
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public boolean isNotReserved(){
        return patient == null;
    }

    public boolean isReserved(){
        return patient != null;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
