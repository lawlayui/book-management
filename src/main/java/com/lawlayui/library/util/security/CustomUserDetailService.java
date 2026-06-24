package com.lawlayui.library.util.security;

import static org.springframework.security.core.userdetails.User.builder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawlayui.library.entity.User;
import com.lawlayui.library.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService { 
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("user with email " + email + " not found"));

        return builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().toString())
        .build();
    }
}
