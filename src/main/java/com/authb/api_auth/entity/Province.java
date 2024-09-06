package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Province {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @ManyToOne
    @JoinColumn (name = "countryId")
    @Getter
    private Country country;
    @Getter
    @Setter
    private String province;
    @OneToMany (mappedBy = "province")
    private List<City>cities;
}
