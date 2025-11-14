package com.pilaka.user_service.controller;

import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.serfvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user") public class UserController {
@Autowired
private UserService userService;
    @GetMapping
    public String sayHello(){
        return "Welcome to User Service";
    }
    @PostMapping("/addUser")
    public AwsUser addUser(@RequestBody AwsUserDTO dto){
        this.userService.addUser(dto);
        return null;
    }
//    @GetMapping("fetchById/{id}")
//    public ResponseEntity<AwsUserDTO> findByUserId(Long id){
//        return userService.fetchUserDetailsById(id);
//    }
}
