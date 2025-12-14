package com.pilaka.user_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String roleName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-userRole")
    private AwsUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    @JsonManagedReference("role-userRole")
    private Role role;


}
