package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String city;
    @OneToMany (mappedBy = "city")
    private List<User> users;
    @ManyToOne
    @JoinColumn (name = "provinceId")
    private Province province;
}
