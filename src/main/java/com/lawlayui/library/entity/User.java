package com.lawlayui.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity<Long>{
    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }

    @Builder
    public User(Long id, String name, String email, String password, Role role) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Column(length = 255)
    private String name;

    @Column(unique = true)
    private String email;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
