package com.pilaka.user_service.dto;




import com.pilaka.user_service.entity.Role;
import com.pilaka.user_service.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    private Set<String> roles = new HashSet<>();

}