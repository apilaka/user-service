//
//package com.pilaka.user_service.mapper;
//
//import com.pilaka.user_service.entity.UserRole;
//import org.mapstruct.Mapper;
//
//
//
//@Mapper(componentModel = "spring")
//public interface RoleMapper {
//
//default UserRole map(UserRole userRole) {
//    if(userRole ==null)return null;
//    UserRole role = new UserRole();
//    role.setId(userRole.getId());
//    role.setRoleName(userRole.getRoleName());
//return role;
//
//}
//
//
//}
