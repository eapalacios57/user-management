package com.pragma.usuarios.application.handler;

import com.pragma.usuarios.application.dto.RegisterUserRequest;

public interface IUserHandler {

    void saveUser(RegisterUserRequest registerUserRequest, String rol) throws IllegalAccessException;

}
