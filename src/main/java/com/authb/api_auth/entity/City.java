package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "city")
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private Long id;
    @Column(name="city_name")
    private String name;
    @OneToMany (mappedBy = "city")
    private Set<User> users;
    @ManyToOne
    @JoinColumn (name = "province_id")
    private Province province;

    public City(Long id, String name, Province province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }
}


