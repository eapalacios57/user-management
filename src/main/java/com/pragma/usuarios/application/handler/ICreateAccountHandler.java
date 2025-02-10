package com.pragma.usuarios.application.handler;

import com.pragma.usuarios.application.dto.AccountClientRequest;
import com.pragma.usuarios.application.dto.AccountEmployeeRequest;
import com.pragma.usuarios.application.dto.AccountOwnerRequest;

public interface ICreateAccountHandler {

    void createAccountOwner(AccountOwnerRequest registerUserRequest, Long rol);
    void createAccountEmployee(AccountEmployeeRequest registerUserRequest);
    void createAccountClient(AccountClientRequest registerUserRequest);


}
