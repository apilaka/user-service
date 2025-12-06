package com.pilaka.user_service.serfvice;

import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.entity.UserRole;
import com.pilaka.user_service.mapper.UserMapper;
import com.pilaka.user_service.repo.AwsUserRepository;
import com.pilaka.user_service.repo.RoleRepository;
import jakarta.transaction.Transactional;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {

    @Autowired
    private AwsUserRepository userRepo;
    @Autowired
    private final UserMapper userMapper;

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
        //S@ibaba1111
    }
    @Transactional
    public AwsUserDTO addNewUser(AwsUserDTO userDTO) {
        AwsUser userToSave = userMapper.mapUserDTOToUser(userDTO);
        Set<UserRole> roles = userDTO.getRoles().stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName()))
                .collect(Collectors.toSet());
        roles.forEach(role->role.setUser(userToSave));
        userToSave.setRoles(roles);
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

            // bind roles to user
            roles.forEach(r -> r.setUser(user));
            user.setRoles(user.getRoles());

            // cascade handles role saving
            return userRepo.save(user);
        });
    }


    public AwsUser createUser(AwsUser user) {

        return transactionTemplate.execute(status -> {
            try {
                return userRepo.save(user);
            } catch (Exception e) {
                status.setRollbackOnly();  // manual rollback
                throw e;
            }
        });
    }

    public void updateRoles(AwsUser user, List<UserRole> roles) {

        transactionTemplate.execute(status -> {
            user.getRoles().clear();
            user.getRoles().addAll(roles);
            return null;
        });
    }

}

