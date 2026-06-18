package com.lawlayui.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawlayui.library.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
