package com.example.medicalonlinebookingservice.entity;

import com.example.medicalonlinebookingservice.entity.enums.Specialist;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "doctors")
public class Doctor extends User {

    private Specialist specialist;

    @ManyToMany
    private List<Visit> visits;

    public Doctor(List<Visit> visits) {
        this.visits = visits;
    }

    public Doctor() {
    }
}
