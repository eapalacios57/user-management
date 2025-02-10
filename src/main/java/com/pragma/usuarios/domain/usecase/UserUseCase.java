package com.pragma.usuarios.domain.usecase;

import com.pragma.usuarios.domain.api.IPasswordEncoderServicePort;
import com.pragma.usuarios.domain.modelo.Role;
import com.pragma.usuarios.domain.utils.Constants;
import com.pragma.usuarios.domain.api.IUserServicePort;
import com.pragma.usuarios.domain.modelo.EnumRol;
import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.domain.spi.IUserPersistencePort;
import com.pragma.usuarios.infraestructure.exception.RolNotFoundException;
import com.pragma.usuarios.infraestructure.exception.UserAlreadyExistsException;
import com.pragma.usuarios.infraestructure.exception.ValidBirthDateException;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderServicePort passwordEncoderServicePort;

    @Override
    public void saveUser(User user, Role role, Long rolId)   {

        User validateUser = userPersistencePort.existsByEmail(user.getEmail());
        if(validateUser != null){
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoderServicePort.encode(user.getPassword()));
        user.setRole(role);

        if(!user.getRole().getId().equals(rolId)){
            throw new RolNotFoundException(Constants.EXCEPTION_ROL);
        }

        if(user.getRole().getName().equals(EnumRol.OWNER.getRol()) && !isAdult(user.getBirthdate())){
            throw new ValidBirthDateException();
        }
        userPersistencePort.saveUser(user);
    }

    @Override
    public User getUser(String email) {
        return userPersistencePort.getUser(email);
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    public boolean isAdult(Date birthDate) {
        LocalDate dateNacFormat = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateNacFormat, currentDate).getYears() >= 18;
    }
}
