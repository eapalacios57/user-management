package com.pragma.usuarios.application.handler.impl;

import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.application.handler.IUserManagementHandler;
import com.pragma.usuarios.application.mapper.UserDetailResponseMapper;
import com.pragma.usuarios.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class UserManagementHandler implements IUserManagementHandler {

    private IUserServicePort userServicePort;
    private UserDetailResponseMapper userDetailResponseMapper;


    @Override
    public UserDetailResponse getUserById(Long id) {
        return userDetailResponseMapper.toUserDetailResponse(userServicePort.getUserById(id));
    }
}
