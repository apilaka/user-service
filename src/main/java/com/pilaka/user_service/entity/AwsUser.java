package com.pilaka.user_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.pilaka.user_service.entity.Role;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
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
    private Long userId;
    private String userPassword;
    private String address;
    private String city;
//    private Set<Role> roles;


    @Column(unique = true)
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

}