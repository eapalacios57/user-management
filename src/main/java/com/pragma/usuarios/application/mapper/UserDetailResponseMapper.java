package com.pragma.usuarios.application.mapper;

import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.domain.modelo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDetailResponseMapper {

    @Mapping(source = "user.role.name", target = "role")
    UserDetailResponse toUserDetailResponse(User user);
}
