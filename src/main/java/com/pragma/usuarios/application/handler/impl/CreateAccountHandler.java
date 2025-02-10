package com.pragma.usuarios.application.handler.impl;

import com.pragma.usuarios.application.dto.AccountClientRequest;
import com.pragma.usuarios.application.dto.AccountEmployeeRequest;
import com.pragma.usuarios.application.dto.AccountOwnerRequest;
import com.pragma.usuarios.application.handler.ICreateAccountHandler;
import com.pragma.usuarios.application.mapper.CreateCountRequestMapper;
import com.pragma.usuarios.domain.api.IRolServicePort;
import com.pragma.usuarios.domain.api.IUserServicePort;
import com.pragma.usuarios.domain.modelo.EnumRol;
import com.pragma.usuarios.domain.modelo.Role;
import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.RolEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateAccountHandler implements ICreateAccountHandler {

    private final IUserServicePort userServicePort;
    private final CreateCountRequestMapper userRequestMapper;
    private final IRolServicePort rolServicePort;


    @Override
    public void createAccountOwner(AccountOwnerRequest accountOwner, Long rol) {
        createAccount(userRequestMapper.toOwner(accountOwner), rol, EnumRol.OWNER.getId());
    }

    @Override
    public void createAccountEmployee(AccountEmployeeRequest accountEmployee) {
        createAccount(userRequestMapper.toEmployee(accountEmployee), accountEmployee.getIdRol(), EnumRol.EMPLOYEE.getId());
    }

    @Override
    public void createAccountClient(AccountClientRequest accountClient) {
        createAccount(userRequestMapper.toClient(accountClient), accountClient.getIdRol(), EnumRol.CLIENT.getId());
    }

    private void createAccount(User user, Long roleId, Long enumRoleId) {
        Role role = rolServicePort.getRole(roleId);
        userServicePort.saveUser(user, role, enumRoleId);
    }
}
