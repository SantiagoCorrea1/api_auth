package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role() {
    }
}
