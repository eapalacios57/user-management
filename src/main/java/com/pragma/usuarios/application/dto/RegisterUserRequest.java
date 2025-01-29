package com.pragma.usuarios.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    private String name;

    private String lastname;

    private String birthdate;

    private String numberDocument;

    private String phone;

    private String email;

    private String password;
}
