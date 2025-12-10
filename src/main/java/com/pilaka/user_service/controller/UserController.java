package com.pilaka.user_service.controller;


import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;

import com.pilaka.user_service.service.AuthService;
import com.pilaka.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/addUser")
    public ResponseEntity<AwsUser> addUser(@RequestBody AwsUserDTO user){
         userService.addUser(user);

         return null;
       // return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<AwsUserDTO> fetchUserDetailsById(@PathVariable Long userId){
        System.out.println(userService.fetchUserDetailsById(userId));
        System.out.println("User Id is : "+userId);
        AwsUserDTO user = userService.fetchUserDetailsById(userId).getBody();
        System.out.println("User Id is : "+user.toString());
        return ResponseEntity.ok(userService.fetchUserDetailsById(userId).getBody());
    }
//    @PostMapping("/addNewUser")
//    public ResponseEntity<AwsUserDTO> addNewUser(@RequestBody AwsUserDTO userDTO) {
//        System.out.println("Adding new user");
//
//        AwsUser user =
//        return new ResponseEntity<>(userService.addUser(userDTO),HttpStatus.CREATED);
//    }
    @GetMapping("/listUsers")
    public List<AwsUser> listUsers(){
        return  userService.listUsers();
    }


    @GetMapping ("/findUserByUsername/{username}")
    public AwsUser findUserByUsername(@PathVariable String username){
        return this.userService.findByUserName(username);
    }
        @PostMapping("/login")
        public ResponseEntity<AwsUser> login(@RequestParam String username, @RequestParam String password) {
            AwsUser awsUser =this.userService.findByUserName(username);
            if(awsUser.getUserName().equals(username) && awsUser.getUserPassword().equals(password))
            {
                awsUser.getUserName();
                this.authService.login(awsUser.getUserName());
            }
            else {
                System.out.println("User not found");
            }
            return ResponseEntity.ok((awsUser));
        }


    @PostMapping("/updateUserWithRoles")
    public ResponseEntity<AwsUser> updateUserWithRoles(@RequestBody AwsUser user) {

     //   userService.saveUserWithRoles(user, user.getRoles());
        System.out.println("Adding new user");
        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }
    }


