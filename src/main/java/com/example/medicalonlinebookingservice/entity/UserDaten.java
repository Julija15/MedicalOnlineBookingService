package com.example.medicalonlinebookingservice.entity;

import com.example.medicalonlinebookingservice.entity.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
public class UserDaten extends AbstractEntity {

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private String house;

    @NotEmpty
    private String flat;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
