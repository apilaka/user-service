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


    public AwsUser addUser(AwsUser user) {
    //    AwsUser savedUser = userRepo.save(userMapper.mapUserDTOToUser(userDTO));
        return userRepo.save(user);

    }

    public ResponseEntity<AwsUserDTO> fetchUserDetailsById(Long userId) {
        System.out.println("1 User Service  fetchUserDetailsById");

        Optional<AwsUser> fetchedUser =  userRepo.findById(userId);
        System.out.println("user Id from repository" +userRepo.findById(userId));
        AwsUserDTO user = userMapper.mapUserToUserDTO(userRepo.findById(userId).get());
        AwsUser foudUser =userRepo.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

    }
    public AwsUserDTO addNewUser(AwsUserDTO userDTO) {
        AwsUser savedUser = userRepo.save(userMapper.mapUserDTOToUser(userDTO));
        return userMapper.mapUserToUserDTO(savedUser);

    }
    public List<AwsUser> listUsers()
    {
        return  userRepo.findAll();
    }
}