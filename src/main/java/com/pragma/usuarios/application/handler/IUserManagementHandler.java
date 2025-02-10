package com.pragma.usuarios.application.handler;

import com.pragma.usuarios.application.dto.UserDetailResponse;

public interface IUserManagementHandler {

    UserDetailResponse getUserById(Long id);
}
