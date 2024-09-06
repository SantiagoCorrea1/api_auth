package com.authb.api_auth.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @OneToMany (mappedBy = "city")
    private List<User> users;

    @ManyToOne
    @JoinColumn (name = "provinceId")
    private Province province;
}
