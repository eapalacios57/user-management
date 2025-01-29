package com.pragma.usuarios.domain.spi;

import com.pragma.usuarios.domain.modelo.Role;

public interface IRolPersistencePort {

    Role getRole(String name);
}
