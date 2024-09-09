package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany (mappedBy = "gender")
    private Set<User> users;

    public Gender(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
