package com.pilaka.user_service.repo;

import com.pilaka.user_service.entity.Role;
import com.pilaka.user_service.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);


}
