package com.pragma.usuarios.application.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @Email(message = "The email is not valid.")
    @NotBlank(message = "The email cannot be empty.")
    private  String email;

    @NotBlank(message = "The password cannot be empty.")
    private String password;

}
