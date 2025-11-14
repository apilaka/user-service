package com.pilaka.user_service.serfvice;

import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.mapper.AwsUserMapper;
import com.pilaka.user_service.repo.AwsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private AwsUserRepository userRepository;


    public AwsUserDTO addUser(AwsUserDTO userDTO) {
    AwsUser user = new AwsUser();
    user.setUserName(userDTO.getUserName());
    user.setCity(userDTO.getCity());
    user.setUserPassword(userDTO.getUserPassword());
    user.setAddress(userDTO.getAddress());
    userRepository.save(user);
    return userDTO;
    }

    public ResponseEntity<AwsUserDTO> fetchUserDetailsById(Long userId) {
        Optional<AwsUser> fetchedUser =  userRepository.findById(userId);
        if(fetchedUser.isPresent())
            return new ResponseEntity<>(AwsUserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

}
