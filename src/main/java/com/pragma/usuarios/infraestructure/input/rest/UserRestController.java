package com.pragma.usuarios.infraestructure.input.rest;

import com.pragma.usuarios.application.dto.AccountClientRequest;
import com.pragma.usuarios.application.dto.MessageResponse;
import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.application.handler.IUserManagementHandler;
import com.pragma.usuarios.infraestructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserManagementHandler userManagementHandler;

    @GetMapping
    public ResponseEntity<UserDetailResponse> getUserById(@RequestParam Long userId)  {
        UserDetailResponse userStatus = userManagementHandler.getUserById(userId);
        return new ResponseEntity<>(userStatus, HttpStatus.OK);
    }
}
