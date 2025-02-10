package com.pragma.usuarios.application.handler.impl;

import com.pragma.usuarios.application.dto.AuthenticationRequest;
import com.pragma.usuarios.application.dto.AuthenticationResponse;
import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.application.handler.IAuthenticationHandler;
import com.pragma.usuarios.application.mapper.UserDetailResponseMapper;
import com.pragma.usuarios.domain.api.IUserServicePort;
import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.infraestructure.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationHandler implements IAuthenticationHandler {

    private final AuthenticationManager authenticationManager;
    private final IUserServicePort userServicePort;
    private final JwtUtils jwtUtils;
    private final UserDetailResponseMapper userDetailResponseMapper;

    public AuthenticationHandler(AuthenticationManager authenticationManager, IUserServicePort userServicePort, JwtUtils jwtUtils,
                UserDetailResponseMapper userDetailResponseMapper) {
        this.authenticationManager = authenticationManager;
        this.userServicePort = userServicePort;
        this.jwtUtils = jwtUtils;
        this.userDetailResponseMapper = userDetailResponseMapper;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        this.authenticationManager.authenticate(authentication);
        User user = userServicePort.getUser(authenticationRequest.getEmail());
        String jwt =  jwtUtils.generateToken(user, jwtUtils.generateExtraClaims(user));
        return new AuthenticationResponse(jwt);

    }



    public UserDetailResponse getUserFromToken(String token) {
        String email = jwtUtils.getEmailFromToken(token);
        User user = userServicePort.getUser(email);
        return userDetailResponseMapper.toUserDetailResponse(user);
    }


}
