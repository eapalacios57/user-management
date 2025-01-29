package com.pragma.usuarios.application.handler;

import com.pragma.usuarios.application.dto.RegisterUserRequest;
import com.pragma.usuarios.application.mapper.UserRequestMapper;
import com.pragma.usuarios.application.utils.ValidationUtils;
import com.pragma.usuarios.domain.api.IUserServicePort;
import com.pragma.usuarios.domain.modelo.EnumRol;
import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.infraestructure.out.jpa.entity.RolEntity;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.RolEntityMapper;
import com.pragma.usuarios.infraestructure.out.jpa.repository.IRolRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final  RolEntityMapper rolEntityMapper;
    private final IRolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegisterUserRequest registerRequest, String type) throws IllegalAccessException {
        if(registerRequest.getName() == null || registerRequest.getLastname() == null
                || registerRequest.getPhone() == null){
            throw new IllegalAccessException("Invalid argument. Please verify the data you are sending.");
        }
        if (!ValidationUtils.emailValid(registerRequest.getEmail())) {
            throw new IllegalArgumentException("The email is not valid.");
        }

        if (!ValidationUtils.phoneValid(registerRequest.getPhone())) {
            throw new IllegalArgumentException("The phone number is not valid.");
        }

        if (!ValidationUtils.documentValid(registerRequest.getNumberDocument())) {
            throw new IllegalArgumentException("The identification document must be numeric.");
        }

        if (type.equals(EnumRol.OWNER.toString()) && !ValidationUtils.adult(registerRequest.getBirthdate())) {
                throw new IllegalArgumentException("The user is not of legal age.");
        }

        User user = userRequestMapper.toUser(registerRequest);
        Optional<RolEntity> rol = rolRepository.findByName(type);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(rolEntityMapper.toRole(rol.orElseThrow(() -> new IllegalArgumentException("Role is required"))));
        userServicePort.saveUser(user);
    }
}
