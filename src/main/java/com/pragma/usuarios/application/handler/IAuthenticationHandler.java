package com.pragma.usuarios.application.handler;

import com.pragma.usuarios.application.dto.AuthenticationRequest;
import com.pragma.usuarios.application.dto.AuthenticationResponse;
import com.pragma.usuarios.application.dto.UserDetailResponse;

public interface IAuthenticationHandler {

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    boolean validateToken(String token);

    UserDetailResponse getUserFromToken(String token);

}
