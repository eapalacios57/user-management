package com.pragma.usuarios.infraestructure.adapter;

import com.pragma.usuarios.domain.api.IPasswordEncoderServicePort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordEncoderAdapter implements IPasswordEncoderServicePort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

}
