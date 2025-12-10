package com.pilaka.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String roleName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private AwsUser user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


}
