package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name ="gender")
@NoArgsConstructor
@Getter
@Setter
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "gender_id")
    private Long id;
    @Column (name = "gender_type")
    private String name;
    @OneToMany (mappedBy = "gender")
    private Set<User> users;

    public Gender(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
