package com.pilaka.user_service.dto;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwsUserDTO {


    private Long userId;
    private String userName;
    private String userPassword;
    private String address;
    private String city;

}