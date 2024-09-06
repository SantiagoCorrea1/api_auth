package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Entity
@Table(name = "userDb")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    private Long id;
    @Getter
    @ManyToOne
    @JoinColumn (name = "idTypeId")
    private IdType idType;
    @ManyToOne
    @JoinColumn (name = "cityId")
    @Getter
    private City city;
    @ManyToOne
    @JoinColumn(name = "genderId")
    @Getter
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "roleId")
    @Getter
    private Role role;
    @Getter
    @Setter
    private String identificationNumber;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private Date birthDate;
    @Getter
    @Setter
    private String phoneNumber;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    public User(IdType idType, String identificationNumber, City city, Gender gender, Role role, String firstName, String lastName, Date birthDate, String phoneNumber, String email, String password) {
        this.idType = idType;
        this.identificationNumber = identificationNumber;
        this.city = city;
        this.gender = gender;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
