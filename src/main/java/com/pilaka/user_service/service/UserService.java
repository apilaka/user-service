package com.pilaka.user_service.service;

//import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.entity.Role;
//import com.pilaka.user_service.entity.UserRole;
import com.pilaka.user_service.entity.UserRole;
import com.pilaka.user_service.mapper.UserMapper;
import com.pilaka.user_service.repo.AwsUserRepository;
import com.pilaka.user_service.repo.RoleRepository;
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
    private RoleRepository roleRepository;


    @Autowired
    private AwsUserRepository awsUserRepository;
    private final TransactionTemplate transactionTemplate;

    public UserService(PlatformTransactionManager txManager, AwsUserRepository repo, UserMapper userMapper) {
        this.transactionTemplate = new TransactionTemplate(txManager);
        this.userRepo = repo;
        this.userMapper = userMapper;
    }

    @Transactional
    public AwsUser addUser(AwsUserDTO userDTO) {

        // 1. Convert DTO → Entity (without roles)
        AwsUser user = userMapper.mapUserDTOToUser(userDTO);
        user.setUserRoles(new HashSet<>());
        // 2. Save user first (so we get userId)
        AwsUser savedUser = userRepo.save(user);
        // 3. Now create UserRole entries
        Set<UserRole> userRoles = new HashSet<>();
        AwsUser finalSavedUser = savedUser;
        for (String roleName : userDTO.getRoles()) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                throw new RuntimeException("Role not found: " + roleName);
            }
            UserRole ur = new UserRole();
            ur.setUser(savedUser);
            ur.setRole(role);
            userRoles.add(ur);
        }
        // 4. Assign roles to saved user
        savedUser.setUserRoles(userRoles);
        // 5. Save again with roles
        savedUser = userRepo.save(savedUser);
        return savedUser;
    }

    public ResponseEntity<AwsUserDTO> fetchUserDetailsById(Long userId) {
        System.out.println("1 User Service  fetchUserDetailsById");
        Optional<AwsUser> fetchedUser = userRepo.findById(userId);
        System.out.println("user Id from repository" + userRepo.findById(userId));
        AwsUserDTO user = userMapper.mapUserToUserDTO(userRepo.findById(userId).get());
        AwsUser foudUser = userRepo.findById(userId).get();
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        //S@ibaba1111
    }
//
    @Transactional
    public AwsUser addNewUser(AwsUserDTO userDTO) {
        AwsUser userToSave = userMapper.mapUserDTOToUser(userDTO);
//        Set<UserRole> roles = userDTO.getRoles().stream()
//                .map(role -> roleRepository.findByRoleName(role.getRoleName()))
//                .collect(Collectors.toSet());
        //roles.forEach(role->role.setUser(userToSave));
        // userToSave.setRoles(roles);
        AwsUser saved = userRepo.save(userToSave);
        return userToSave;
    }
//

@Transactional
public List<AwsUser> listUsers() {
        return userRepo.findAll();
    }

    public AwsUser findByUserName(String username){
        return  this.userRepo.findByUserName(username).get();
    }


//    @Transactional
//    public AwsUser saveUserWithRoles(AwsUser user, Set<UserRole> roles) {
//
//        return transactionTemplate.execute(status -> {
//
//            // bind roles to user
//            //      roles.forEach(r -> r.setUser(user));
//            // user.setRoles(user.getRoles());
//
//            // cascade handles role saving
//            return userRepo.save(user);
//        });
//    }


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


//    public AwsUser createUserWithRoles(String username, String password, Set<String> roleNames) {
//
//        // Create or find roles
//        Set<Role> roles = roleNames.stream()
//                .map(roleName -> roleRepository.save(
//                        Role.builder().roleName(roleName).build()
//                ))
//                .collect(java.util.stream.Collectors.toSet());
//
//        // Create user
//        AwsUser user = AwsUser.builder()
//                .userName(username)
//                .userPassword(password)
//                .roles(roles) // assign roles
//                .build();
//
//        return userRepo.save(user);
//
//    }
}
