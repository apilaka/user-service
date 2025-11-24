package com.pilaka.user_service;

import com.pilaka.user_service.dto.AwsUserDTO;
import com.pilaka.user_service.entity.AwsUser;
import com.pilaka.user_service.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.pilaka.user_service")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	@LoadBalanced
//	public UserMapper userMapper() {
//		return new UserMapper() {
//			@Override
//			public AwsUser mapUserDTOToUser(AwsUserDTO userDTO) {
//				return null;
//			}
//
//
//
//			@Override
//			public AwsUserDTO mapUserToUserDTO(AwsUser user) {
//				return null;
//			}
//		};
//	}




}
