package com.pragma.usuarios.infraestructure.out.jpa.mapper;

import com.pragma.usuarios.domain.modelo.Role;
import com.pragma.usuarios.infraestructure.out.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RolEntityMapper {

    RolEntity toEntity(Role role);

    Role toRole(RolEntity rolEntity);
}
