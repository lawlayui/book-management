package com.lawlayui.library.entity;

import java.time.Instant;

import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken extends BaseEntity<Long>{

    @UUID
    @Column(updatable = false, unique = true, name = "uuid", nullable = false)
    private UUID uuid; 

    @Column(nullable = false, unique =  true)
    private String token;

    @Column(nullable = false)
    private Instant expired_time;
}
