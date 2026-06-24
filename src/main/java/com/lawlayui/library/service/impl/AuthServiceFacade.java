package com.lawlayui.library.service.impl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.AuthLoginRequest;
import com.lawlayui.library.entity.RefreshToken;
import com.lawlayui.library.entity.User;
import com.lawlayui.library.entity.User.Role;
import com.lawlayui.library.exception.EmailAlreadyExistsException;
import com.lawlayui.library.exception.TokenHasNotExpiredException;
import com.lawlayui.library.exception.TokenNotFoundException;
import com.lawlayui.library.repository.RefreshTokenRepository;
import com.lawlayui.library.repository.UserRepository;
import com.lawlayui.library.service.Authable;
import com.lawlayui.library.util.security.TokenService;
import com.nimbusds.jwt.SignedJWT;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceFacade implements Authable{
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthServiceFacade(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public List<String> login(AuthLoginRequest userLoginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
        
        String uuidRandom = UUID.randomUUID().toString();
        String refreshId = refreshTokenRepository.save(RefreshToken.builder().expired_time(Instant.now().plus(7, ChronoUnit.DAYS)).token(uuidRandom).build()).getUuid().toString();

        return List.of(tokenService.generateToken(authentication, uuidRandom), refreshId) ;
    }

    @Override
    @Transactional
    public void register(UserCreateRequest userCreateRequest) throws EmailAlreadyExistsException{
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new EmailAlreadyExistsException("email alredy exists");
        }

        User newUser = User.builder()
        .name(userCreateRequest.getName())
        .email(userCreateRequest.getEmail())
        .password(passwordEncoder.encode(userCreateRequest.getPassword()))
        .role(Role.ROLE_USER)
        .build();

        userRepository.save(newUser);
    }
    
    @Override
    @Transactional
    public String refresh(String uuidRefreshToken, SignedJWT signedJWT) throws TokenHasNotExpiredException, TokenNotFoundException, ParseException{
        var jwt = signedJWT.getJWTClaimsSet();


            if (Instant.now().isBefore(jwt.getExpirationTime().toInstant().minusSeconds(300))) {
                throw new TokenHasNotExpiredException();
            }

            RefreshToken rt = refreshTokenRepository.findByToken(jwt.getClaimAsString("token")).orElseThrow(() -> new TokenNotFoundException());
            if (Instant.now().isBefore(rt.getExpired_time())) {
                throw new TokenNotFoundException();
            }
            if (rt.getUuid().toString() != uuidRefreshToken) {
                throw new TokenNotFoundException();
            }
            
            String randomUuid = UUID.randomUUID().toString();
            String token = tokenService.generateToken(jwt.getSubject(), jwt.getStringListClaim("roles"), randomUuid);

            rt.setToken(randomUuid);
            refreshTokenRepository.save(rt);

            return token;
    }
}
