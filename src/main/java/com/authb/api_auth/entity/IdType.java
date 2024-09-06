package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class IdType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String type;
    @OneToMany(mappedBy = "idType")
    private List<User>users;

    public IdType(String type) {
        this.type = type;
    }
    public IdType() {
    }
}
