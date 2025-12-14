package com.pilaka.user_service.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
    @Column(unique = true)
    private String userName;
    private Long userId;
    private String userPassword;
    private String address;
    private String city;
//    private Set<Role> roles;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-userRole")
    private Set<UserRole> userRoles = new HashSet<>();

}