package com.example.medicalonlinebookingservice.entity;

import com.example.medicalonlinebookingservice.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData extends AbstractEntity {
    @Setter
    @Getter
    @NotEmpty
    private String city;

    @Setter
    @Getter
    @NotEmpty
    private String street;

    @Setter
    @Getter
    @NotEmpty
    private String house;

    @Setter
    @Getter
    @NotEmpty
    private String flat;


    @OneToOne
    private User user;


  //  @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Setter
    @Getter
    private LocalDate dateOfBirth;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
