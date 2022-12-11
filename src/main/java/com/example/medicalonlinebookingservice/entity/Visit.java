package com.example.medicalonlinebookingservice.entity;

import com.example.medicalonlinebookingservice.entity.enums.Specialist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "visits")
public class Visit extends AbstractEntity {

    public Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public LocalDateTime startOfVisit;

    public Visit(Doctor doctor, LocalDateTime time)
    {
        this.doctor = doctor;
        this.startOfVisit = time;
        this.createdAt = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReserved(){
        return user != null;
    }
}
