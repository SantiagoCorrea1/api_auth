package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table (name="province")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Province {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "province_id")
    private Long id;
    @Column (name = "province_name")
    private String name;
    @ManyToOne
    @JoinColumn (name = "country_id")
    private Country country;
    @OneToMany (mappedBy = "province")
    private Set<City> cities;
}
