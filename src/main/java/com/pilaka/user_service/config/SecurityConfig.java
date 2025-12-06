package com.pilaka.user_service.config;

import com.pilaka.user_service.serfvice.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // PUBLIC ENDPOINTS
//                        .requestMatchers("/user/login", "/auth/register").permitAll()
//                        // INTERNAL SERVICE-TO-SERVICE (if needed)
//                        .requestMatchers("/internal/**").hasRole("SYSTEM")
//                        // ADMIN ONLY
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        // USER + ADMIN
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        // Everything else must be authenticated
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//        return null;
//    }

//
//    public static UserDetails admin() {
//        return User.builder()
//                .username("apilaka")
//                .password("securePassword123") // {noop} = no password encoding
//                .roles("ADMIN")
//                .build();
//    }
//
//    public static UserDetails normal() {
//        return User.builder()
//                .username("testUser")
//                .password("securePassword123")
//                .roles("USER")
//                .build();
//    }
}
