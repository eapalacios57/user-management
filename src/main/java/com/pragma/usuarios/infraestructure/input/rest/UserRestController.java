package com.pragma.usuarios.infraestructure.input.rest;


import com.pragma.usuarios.application.dto.MessageResponse;
import com.pragma.usuarios.application.dto.RegisterUserRequest;
import com.pragma.usuarios.application.handler.IUserHandler;
import com.pragma.usuarios.domain.modelo.EnumRol;
import com.pragma.usuarios.infraestructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/owner")
    public ResponseEntity<MessageResponse> createOwner(@RequestBody RegisterUserRequest registerUserRequest) throws IllegalAccessException {
         userHandler.saveUser(registerUserRequest, EnumRol.OWNER.name());
         MessageResponse messageResponse = new MessageResponse(Constants.OK);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/employee")
    public ResponseEntity<MessageResponse> createEmployee(@RequestBody RegisterUserRequest registerUserRequest) throws IllegalAccessException {
        userHandler.saveUser(registerUserRequest, EnumRol.EMPLOYEE.name());
        MessageResponse messageResponse = new MessageResponse(Constants.OK);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/client")
    public ResponseEntity<MessageResponse> createClient(@RequestBody RegisterUserRequest registerUserRequest) throws IllegalAccessException {
        userHandler.saveUser(registerUserRequest, EnumRol.CLIENT.name());
        MessageResponse messageResponse = new MessageResponse(Constants.OK);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }
}
