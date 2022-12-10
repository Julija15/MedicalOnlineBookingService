package com.example.medicalonlinebookingservice.entity;

import com.example.medicalonlinebookingservice.entity.enums.Specialist;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "visits")
public class Visit extends AbstractEntity {

    public Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public LocalDate startOfVisit;

    public LocalDate dataVisit;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
