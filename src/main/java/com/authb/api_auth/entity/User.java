package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Entity
@Table(name = "userDb")
@AllArgsConstructor
@NoArgsConstructor
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
}
