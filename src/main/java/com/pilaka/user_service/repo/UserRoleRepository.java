package com.pilaka.user_service.repo;

//import com.pilaka.user_service.entity.Role;
import com.pilaka.user_service.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {}

