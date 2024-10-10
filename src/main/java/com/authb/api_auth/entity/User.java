package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;
    @ManyToOne
    @JoinColumn (name = "identification_type_id")
    private IdType idType;
    @ManyToOne
    @JoinColumn (name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "identification_number", unique = true)
    private String identificationNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "mail", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "url_avatar")
    private String avatarUrl;
    @Column (name = "address")
    private String address;
}
