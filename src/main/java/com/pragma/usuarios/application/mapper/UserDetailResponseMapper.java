package com.pragma.usuarios.application.mapper;

import com.pragma.usuarios.application.dto.UserDetailResponse;
import com.pragma.usuarios.domain.modelo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDetailResponseMapper {

    @Mapping(target = "names", source = ".", qualifiedByName = "mapNames")
    @Mapping(source = "role.name", target = "role")
    UserDetailResponse toUserDetailResponse(User user);

    @Named("mapNames")
    default String mapNames(User user) {
        return user.getName() + " " + user.getLastname();
    }
}
