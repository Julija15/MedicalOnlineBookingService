package com.example.medicalonlinebookingservice.entity;

import com.example.medicalonlinebookingservice.entity.enums.Gender;
import com.example.medicalonlinebookingservice.entity.enums.Role;
import com.example.medicalonlinebookingservice.entity.enums.Specialist;
import com.example.medicalonlinebookingservice.entity.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(message = "Store name length must be 2 - 16", min = 2, max = 16)
    @NotBlank(message = "Field must not be empty")
    private String firstname;

    @Size(message = "Store name length must be 2 - 16", min = 2, max = 16)
    @NotBlank(message = "Field must not be empty")
    private String lastname;

    @Size(message = "Store name length must be 2 - 16", min = 2, max = 16)
    @NotBlank(message = "Field must not be empty")
    private String username;

    @NotBlank(message = "Field must not be empty")
    @Size(message = "Password length must be 5-10 characters!", min = 5, max = 10)
    @Pattern(message = "Password must consist of numbers and latin letters!", regexp = "[\\w\\d]+")
    private String password;

    @NotBlank(message = "Field must not be empty")
    private String email;

    private Specialist specialist;

    @NotEmpty
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @NotNull
    @Setter
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Visit> visitList;

    @OneToOne
    private UserData userData;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
