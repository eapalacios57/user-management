package com.pragma.usuarios.infraestructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pragma.usuarios.application.dto.ApiError;
import com.pragma.usuarios.application.handler.impl.CustomUserDetailsHandler;
import com.pragma.usuarios.domain.api.IPasswordEncoderServicePort;
import com.pragma.usuarios.domain.api.IRolServicePort;
import com.pragma.usuarios.domain.api.IUserServicePort;
import com.pragma.usuarios.domain.spi.IRolPersistencePort;
import com.pragma.usuarios.domain.spi.IUserPersistencePort;
import com.pragma.usuarios.domain.usecase.RolUseCase;
import com.pragma.usuarios.domain.usecase.UserUseCase;
import com.pragma.usuarios.infraestructure.adapter.PasswordEncoderAdapter;
import com.pragma.usuarios.infraestructure.out.jpa.adapter.RolJpaAdapter;
import com.pragma.usuarios.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.RolEntityMapper;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.usuarios.infraestructure.out.jpa.repository.IRolRepository;
import com.pragma.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.time.LocalDateTime;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IPasswordEncoderServicePort passwordEncoderServicePort(){
        return new PasswordEncoderAdapter(passwordEncoder());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), passwordEncoderServicePort());
    }

    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort(){
        return new RolUseCase(rolPersistencePort());
    }


}
