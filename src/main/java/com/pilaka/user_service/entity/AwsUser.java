package com.pilaka.user_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "awsUser",
uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class AwsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aws_user_seq")
    @SequenceGenerator(
            name = "aws_user_seq",
            sequenceName = "AWS_USER_SEQ",
            allocationSize = 1
    )

    private String userPassword;
    private String address;
    private String city;
    private Long userId;

    @Column(unique = true)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> roles = new HashSet<>();
}