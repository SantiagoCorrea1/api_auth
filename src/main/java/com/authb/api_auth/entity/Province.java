package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Province {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "countryId")
    private Country country;
    @Getter
    @Setter
    private String province;
    @Getter
    @OneToMany (mappedBy = "province")
    private List<City>cities;
}
