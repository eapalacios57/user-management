package com.pragma.usuarios.application.mapper;

import com.pragma.usuarios.application.dto.RegisterUserRequest;
import com.pragma.usuarios.domain.modelo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    @Mapping(target = "birthdate", expression = "java(new java.util.Date())")
    User toUser(RegisterUserRequest userRequest);

}
