package com.pragma.usuarios.infraestructure.input.rest;


import com.pragma.usuarios.application.dto.AccountClientRequest;
import com.pragma.usuarios.application.dto.AccountEmployeeRequest;
import com.pragma.usuarios.application.dto.AccountOwnerRequest;
import com.pragma.usuarios.application.dto.MessageResponse;
import com.pragma.usuarios.application.handler.ICreateAccountHandler;
import com.pragma.usuarios.domain.modelo.EnumRol;
import com.pragma.usuarios.infraestructure.utils.Constants;
import jakarta.validation.Valid;
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
public class CreateAccountRestController {

    private final ICreateAccountHandler createAccountHandler;

    @PostMapping("/owner")
    public ResponseEntity<MessageResponse> createOwner(@Valid @RequestBody AccountOwnerRequest accountOwnerRequest)  {
        createAccountHandler.createAccountOwner(accountOwnerRequest, EnumRol.OWNER.getId());
         MessageResponse messageResponse = new MessageResponse(Constants.OK);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/employee")
    public ResponseEntity<MessageResponse> createEmployee(@Valid @RequestBody AccountEmployeeRequest accountEmployeeRequest)  {
        createAccountHandler.createAccountEmployee(accountEmployeeRequest);
        MessageResponse messageResponse = new MessageResponse(Constants.OK);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PostMapping("/client")
    public ResponseEntity<MessageResponse> createClient(@Valid @RequestBody AccountClientRequest accountClientRequest)  {
        createAccountHandler.createAccountClient(accountClientRequest);
        MessageResponse messageResponse = new MessageResponse(Constants.OK);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }
}
