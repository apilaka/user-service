package com.pilaka.user_service.dto;


import com.pilaka.user_service.entity.UserRole;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwsUserDTO {


    private Long userId;
    private String userName;
    private String userPassword;
    private String address;
    private String city;
    private Set<UserRole> roles;

}