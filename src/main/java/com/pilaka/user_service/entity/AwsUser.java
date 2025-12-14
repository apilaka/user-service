package com.pilaka.user_service.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity

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

    @Column(unique = true)
    private String userName;
    private String userPassword;
    private String address;
    private String city;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-userRole")
    private Set<UserRole> userRoles = new HashSet<>();

}