package com.pragma.usuarios.domain.api;

import com.pragma.usuarios.domain.modelo.Role;
import com.pragma.usuarios.domain.modelo.User;

public interface IUserServicePort {

    void saveUser(User user, Role role, Long id);

    User getUser(String email);

    User getUserById(Long id);


}
