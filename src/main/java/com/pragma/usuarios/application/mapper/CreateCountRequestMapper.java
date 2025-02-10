package com.pragma.usuarios.application.mapper;

import com.pragma.usuarios.application.dto.AccountClientRequest;
import com.pragma.usuarios.application.dto.AccountEmployeeRequest;
import com.pragma.usuarios.application.dto.AccountOwnerRequest;
import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.infraestructure.exception.FormatDateInvalidException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CreateCountRequestMapper {

    @Mapping(target = "birthdate", expression = "java(convertStringToDate(accountOwner.getBirthdate()))")
    User toOwner(AccountOwnerRequest accountOwner);
    default Date convertStringToDate(String birthdate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        } catch (ParseException e) {
            throw new FormatDateInvalidException();
        }
    }

    @Mapping(target = "birthdate", ignore = true)
    @Mapping(source = "idRol", target = "role.id")
    User toEmployee(AccountEmployeeRequest accountEmployee);

    @Mapping(target = "birthdate", ignore = true)
    User toClient(AccountClientRequest accountClient);


}
