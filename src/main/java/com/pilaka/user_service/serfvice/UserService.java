package com.pilaka.user_service.serfvice;

import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.mapper.UserMapper;
import com.pilaka.user_service.repo.AwsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    AwsUserRepository userRepo;

    @Autowired
    private final UserMapper userMapper;






    public UserService(UserMapper userMapper) {
        this.userMapper=userMapper;
    }


    public AwsUserDTO addUser(AwsUserDTO userDTO) {
        AwsUser savedUser = userRepo.save(userMapper.mapUserDTOToUser(userDTO));
        return userMapper.mapUserToUserDTO(savedUser);

    }

    public ResponseEntity<AwsUser> fetchUserDetailsById(Long userId) {
        System.out.println("1 User Service  fetchUserDetailsById");
        AwsUser foudUser =userRepo.findById(userId).get();
        Optional<AwsUser> fetchedUser =  userRepo.findById(userId);
        System.out.println("user Id from repository" +userRepo.findById(userId));
       AwsUserDTO user = userMapper.mapUserToUserDTO(fetchedUser.get());
       // System.out.println("User id " +user.toString());
//        if(fetchedUser.isPresent())
//            return new ResponseEntity<>userMapper.mapUserToUserDTO(fetchedUser.get(), HttpStatus.OK);
        return new ResponseEntity<>(foudUser, HttpStatus.NOT_FOUND);

    }

    public List<AwsUser> listUsers()
    {
        return  userRepo.findAll();
    }
}