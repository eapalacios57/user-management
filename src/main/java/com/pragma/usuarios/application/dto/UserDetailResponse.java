package com.pragma.usuarios.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponse {

    private Long id;

    private String email;

    private String role;

    private Boolean tokenValid;
}
