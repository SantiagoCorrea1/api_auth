package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userDb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn (name = "idTypeId")
    private IdType idType;
    @ManyToOne
    @JoinColumn (name = "cityId")
    private City city;
    @ManyToOne
    @JoinColumn(name = "genderId")
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String password;
}
