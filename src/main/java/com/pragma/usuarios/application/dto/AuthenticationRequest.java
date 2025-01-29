package com.pragma.usuarios.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private  String email;

    private String password;

}
