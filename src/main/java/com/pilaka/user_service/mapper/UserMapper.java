package com.pilaka.user_service.mapper;
import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.entity.Role;
import com.pilaka.user_service.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AwsUserDTO mapUserToUserDTO(AwsUser user);



    // Entity → DTO
    @Mapping(target = "roleId", ignore = true)
    Role map(UserRole entity);

    // DTO → Entity
    @Mapping(target = "user", ignore = true)

    UserRole map(Role dto);

//    // Set<UserRole> → Set<Role>
//    Set<Role> map(Set<UserRole> entities);

    @Mapping(target = "userRoles", ignore = true)
    AwsUser mapUserDTOToUser(AwsUserDTO dto);


}