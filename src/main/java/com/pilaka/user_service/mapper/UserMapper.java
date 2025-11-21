package com.pilaka.user_service.mapper;
import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

      //  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        AwsUser mapUserDTOToUser(AwsUserDTO userDTO);
        AwsUserDTO mapUserToUserDTO(AwsUser user);


}
