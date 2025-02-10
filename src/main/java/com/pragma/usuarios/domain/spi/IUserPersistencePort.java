package com.pragma.usuarios.domain.spi;

import com.pragma.usuarios.domain.modelo.User;

public interface IUserPersistencePort {

    void saveUser(User user);

    User getUser(String email);

    User getUserById(Long id);

    User existsByEmail(String email);


}
