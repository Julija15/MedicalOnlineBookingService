package com.example.medicalonlinebookingservice.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "doctors")
public class Doctor extends User {
    @ManyToMany
    private List<Visit> visits;

    public Doctor(List<Visit> visits) {
        this.visits = visits;
    }

    public Doctor() {
    }
}
