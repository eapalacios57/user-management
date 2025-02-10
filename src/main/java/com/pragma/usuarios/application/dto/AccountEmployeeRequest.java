package com.pragma.usuarios.application.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEmployeeRequest extends AccountOwnerRequest{

    @NotNull(message = "The restaurant is not valid.")
    private Long restaurantId;

}
