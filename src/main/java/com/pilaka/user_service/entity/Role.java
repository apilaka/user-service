package com.pilaka.user_service.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonBackReference("role-userRole")
    private Set<UserRole> userRoles = new HashSet<>();
}
