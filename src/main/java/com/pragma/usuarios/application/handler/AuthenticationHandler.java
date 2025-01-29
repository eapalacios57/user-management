package com.pragma.usuarios.application.handler;

import com.pragma.usuarios.application.dto.AuthenticationRequest;
import com.pragma.usuarios.application.dto.AuthenticationResponse;
import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.application.mapper.UserDetailResponseMapper;
import com.pragma.usuarios.domain.api.IUserServicePort;
import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.application.handler.security.JwtHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationHandler implements  IAuthenticationHandler{

    private final AuthenticationManager authenticationManager;
    private final IUserServicePort userServicePort;
    private final JwtHandler jwtHandler;
    private final UserDetailResponseMapper userDetailResponseMapper;

    public AuthenticationHandler(AuthenticationManager authenticationManager, IUserServicePort userServicePort, JwtHandler jwtHandler,
                UserDetailResponseMapper userDetailResponseMapper) {
        this.authenticationManager = authenticationManager;
        this.userServicePort = userServicePort;
        this.jwtHandler = jwtHandler;
        this.userDetailResponseMapper = userDetailResponseMapper;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        this.authenticationManager.authenticate(authentication);
        User user = userServicePort.getUser(authenticationRequest.getEmail());
        String jwt =  jwtHandler.generateToken(user, generateExtraClaims(user));
        return new AuthenticationResponse(jwt);

    }

    @Override
    public boolean validateToken(String token) {
        try{
            this.jwtHandler.extractUsername(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        return extraClaims;
    }

    public UserDetailResponse getUserFromToken(String token) {
        String email = jwtHandler.getEmailFromToken(token);
        User user = userServicePort.getUser(email);
        return userDetailResponseMapper.toUserDetailResponse(user);
    }


}
