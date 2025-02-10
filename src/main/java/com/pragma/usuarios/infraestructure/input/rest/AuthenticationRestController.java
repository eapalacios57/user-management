package com.pragma.usuarios.infraestructure.input.rest;

import com.pragma.usuarios.application.dto.AuthenticationRequest;
import com.pragma.usuarios.application.dto.AuthenticationResponse;
import com.pragma.usuarios.application.handler.IAuthenticationHandler;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final IAuthenticationHandler authenticationHandler;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse response = authenticationHandler.login(authenticationRequest);
        return ResponseEntity.ok(response);
    }




}
