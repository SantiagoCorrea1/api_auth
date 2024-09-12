package com.authb.api_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "permission")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class  Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;
    @Column(name = "permission_name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

}
