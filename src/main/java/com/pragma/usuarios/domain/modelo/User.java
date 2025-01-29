package com.pragma.usuarios.domain.modelo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private  Long id;

    private String name;

    private String lastname;

    private String numberDocument;

    private String phone;

    private Date birthdate;

    private String email;

    private String password;

    private Role role;
}
