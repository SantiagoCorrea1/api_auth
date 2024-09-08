package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String name;
    @OneToMany (mappedBy = "city")
    private List<User> users;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn (name = "provinceId")
    private Province province;

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}


