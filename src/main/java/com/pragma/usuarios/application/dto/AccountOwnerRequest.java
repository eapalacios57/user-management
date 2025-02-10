package com.pragma.usuarios.application.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountOwnerRequest {

    @NotBlank(message = "The name must not be empty.")
    private String name;

    @NotBlank(message = "The lastname must not be empty.")
    private String lastname;

    private String birthdate;

    @Pattern(regexp = "\\d+", message = "The identification document must be numeric.")
    private String numberDocument;

    @Pattern(regexp = "^\\+?\\d{1,13}", message = "The phone number can contain a maximum of 13 characters ")
    @NotBlank(message = "The phone must not be empty..")
    private String phone;

    @Email(message = "The email is not valid.")
    @NotBlank(message = "The email must not be empty.")
    private String email;

    @NotBlank(message = "The password must not be empty.")
    private String password;

    @Positive
    @NotNull(message = "The rol is not valid.")
    private Long idRol;
}
