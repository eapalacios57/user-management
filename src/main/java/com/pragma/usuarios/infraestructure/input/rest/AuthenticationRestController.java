package com.pragma.usuarios.infraestructure.input.rest;

import com.pragma.usuarios.application.dto.AuthenticationRequest;
import com.pragma.usuarios.application.dto.AuthenticationResponse;
import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.application.handler.IAuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final IAuthenticationHandler authenticationHandler;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse response = authenticationHandler.login(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<UserDetailResponse> validateToken(@RequestHeader("Authorization") String token) {

            boolean isValid = authenticationHandler.validateToken(token);
            if (isValid) {
                UserDetailResponse userDetail = authenticationHandler.getUserFromToken(token);
                userDetail.setTokenValid(true);
                return ResponseEntity.ok(userDetail);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }



}
}
