package com.pilaka.user_service.serfvice;

import com.pilaka.user_service.entity.AwsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String login(String username) {
        String token =null;

        AwsUser user = this.userService.findByUserName(username);
        if(user.getUserName().equals("apilaka") && user.getUserPassword().equals("securePassword123")) {
         token = jwtUtil.generateToken(user.toString());
       //  System.out.print("User from token: " +jwtUtil.extractUsername( token.toString()));

        }

        System.out.print("User from token: " +jwtUtil.extractUsername( token.toString()));
        return token;
    }
    }
