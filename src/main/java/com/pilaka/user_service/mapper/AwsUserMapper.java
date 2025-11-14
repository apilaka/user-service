package com.pilaka.user_service.mapper;
import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AwsUserMapper {


    AwsUserMapper INSTANCE = Mappers.getMapper(AwsUserMapper.class);

    AwsUserMapper mapUserDTOToUser(AwsUserDTO userDTO);
    AwsUserDTO mapUserToUserDTO(AwsUser user);
}
