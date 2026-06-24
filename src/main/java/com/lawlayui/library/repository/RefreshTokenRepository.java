package com.lawlayui.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawlayui.library.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String uuid);
    boolean existsByToken(String uuid);
}
