package com.pilaka.user_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.AbstractDocument;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "roles")
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole  {



    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "ROLES_SEQ", allocationSize = 1)
    @Column(name = "ROLE_ID")
    @JsonIgnore
    private Long id;
//    private Long userId;
    private String roleName;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private AwsUser user;

    public AwsUser getUser() {
        return user;
    }

    public void setUser(AwsUser user) {
        this.user = user;
    }

    public UserRole(String roleName, Long userId) {
        this.roleName=roleName;

    }

    public Long getRoleId() {
        return this.id;
    }
}
