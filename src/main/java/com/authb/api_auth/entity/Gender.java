package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String gender;
    @OneToMany (mappedBy = "gender")
    private List<User> users;
    public Gender(String gender) {
        this.gender = gender;
    }
    public Gender() {
    }
}
