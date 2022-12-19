package com.example.medicalonlinebookingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
