package com.pilaka.user_service.serfvice;

import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.entity.UserRole;
import com.pilaka.user_service.mapper.UserMapper;
import com.pilaka.user_service.repo.AwsUserRepository;
import com.pilaka.user_service.repo.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;


@Service
@Transactional
public class UserService {

    @Autowired
    private AwsUserRepository userRepo;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    private final TransactionTemplate transactionTemplate;

    public UserService(PlatformTransactionManager txManager, AwsUserRepository repo, UserMapper userMapper) {
        this.transactionTemplate = new TransactionTemplate(txManager);
        this.userRepo = repo;
        this.userMapper = userMapper;
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
    @Transactional
    public AwsUserDTO addNewUser(AwsUserDTO userDTO) {
        AwsUser userToSave = userMapper.mapUserDTOToUser(userDTO);
      // userToSave.setRoles(userDTO.getRoles());
       // System.out.println(userToSave.getRoles());

        userToSave.setUserId(userDTO.getUserId());
        AwsUser saved = userRepo.save(userToSave);
        return userMapper.mapUserToUserDTO(saved);
    }
    public List<AwsUser> listUsers()
    {
        return  userRepo.findAll();
    }
    public AwsUser findByUserName(String username){
        return  this.userRepo.findByUserName(username).get();
    }

    @Transactional
    public AwsUser saveUserWithRoles(AwsUser user, Set<UserRole> roles) {
        return transactionTemplate.execute(status -> {
            return userRepo.save(user);
        });
    }
    @Transactional
    public AwsUserDTO createNewUser( AwsUserDTO userDTO) {
        AwsUser existingUser =userMapper.mapUserDTOToUser(userDTO);
        // update basic fields
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setUserPassword(userDTO.getUserPassword());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setCity(userDTO.getCity());
        AwsUser saved = userRepo.save(existingUser);
        System.out.println("Roles are" +userDTO.getRoles());
        userDTO.getRoles().forEach(role -> {
            role.setUser(saved);    // Set FK
            roleRepository.save(role);
        });
        return userMapper.mapUserToUserDTO(saved);
    }

    @Transactional
    public void updateRoles(){
        UserRole role = new UserRole(1l, "ADMIN");
        roleRepository.save(role);

    }
}

