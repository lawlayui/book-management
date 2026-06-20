package com.lawlayui.library.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.UserLoginRequest;
import com.lawlayui.library.entity.User;
import com.lawlayui.library.entity.User.Role;
import com.lawlayui.library.exception.EmailAlreadyExistsException;
import com.lawlayui.library.repository.UserRepository;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(UserLoginRequest userLoginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
    
        return tokenService.generateToken(authentication);
    }

    public void registerUser(UserCreateRequest userCreateRequest) throws EmailAlreadyExistsException{
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            new EmailAlreadyExistsException("email alredy exists");
        }

        User newUser = User.builder()
        .name(userCreateRequest.getName())
        .email(userCreateRequest.getEmail())
        .password(passwordEncoder.encode(userCreateRequest.getPassword()))
        .role(Role.ROLE_USER)
        .build();

        userRepository.save(newUser);
    }
}
