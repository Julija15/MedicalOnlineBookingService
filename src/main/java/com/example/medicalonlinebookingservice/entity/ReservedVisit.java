package com.example.medicalonlinebookingservice.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class ReservedVisit extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private List<Visit> visitList;


    public ReservedVisit(List<Visit> visitList) {
        this.visitList = visitList;
    }

    public ReservedVisit() {
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
