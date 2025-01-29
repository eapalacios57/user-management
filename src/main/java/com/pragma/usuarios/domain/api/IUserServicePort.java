package com.pragma.usuarios.domain.api;

import com.pragma.usuarios.domain.modelo.User;

public interface IUserServicePort {

    void saveUser(User user);

    User getUser(String email);


}
