package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private String name;
    @OneToMany (mappedBy = "province")
    private List<City>cities;
}
