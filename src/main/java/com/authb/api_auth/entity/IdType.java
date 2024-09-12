package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "identificationType")
@NoArgsConstructor
@Getter
@Setter
public class IdType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "identification_type_id")
    private Long id;
    @Column (name = "identification_type")
    private String name;
    @OneToMany(mappedBy = "idType")
    private Set<User> users;

    public IdType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
