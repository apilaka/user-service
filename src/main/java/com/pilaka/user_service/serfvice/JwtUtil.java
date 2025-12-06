package com.pilaka.user_service.serfvice;
import com.pilaka.user_service.entity.AwsUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

//    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private final long EXPIRATION_TIME = 3600000; // 1 hour


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;
    String key = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());

    // Extract username from token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    private Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();


    }

    // Validate token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }


    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)

                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
//    public UserDetails getUserDetails(String token) {
//        String username = extractUsername(token);
//        return User.builder()
//                .username(username)
//                .password("")
//                .roles(extractClaims(token).get("roles", String.class))
//                .build();
//    }


}
