package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "country_id")
    private Long id;
    @Column (name = "country_name")
    private String name;
    @OneToMany(mappedBy = "country")
    private Set<Province> provinces;


}
