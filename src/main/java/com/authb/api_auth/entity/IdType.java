package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor

public class IdType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @OneToMany(mappedBy = "idType")
    private List<User>users;

    public IdType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
