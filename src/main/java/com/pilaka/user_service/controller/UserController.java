package com.pilaka.user_service.controller;


import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.serfvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<AwsUser> addUser(@RequestBody AwsUser user){
        AwsUser savedUser =  userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<AwsUserDTO> fetchUserDetailsById(@PathVariable Long userId){
        System.out.println(userService.fetchUserDetailsById(userId));
        System.out.println("User Id is : "+userId);
        AwsUserDTO user = userService.fetchUserDetailsById(userId).getBody();
        System.out.println("User Id is : "+user.toString());
        return ResponseEntity.ok(userService.fetchUserDetailsById(userId).getBody());
    }
@PostMapping("/addNewUser")
public ResponseEntity<AwsUserDTO> addNewUser(@RequestBody AwsUserDTO userDTO) {
    return new ResponseEntity<>(userService.addNewUser(userDTO),HttpStatus.CREATED);
}
    @GetMapping("/listUsers")
    public List<AwsUser> listUsers(){
        return  userService.listUsers();
    }
}