package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @OneToMany(mappedBy = "country")
    private List<Province> provinces;

    public Country(String name) {
        this.name = name;
    }
    public Country() {
    }
}
