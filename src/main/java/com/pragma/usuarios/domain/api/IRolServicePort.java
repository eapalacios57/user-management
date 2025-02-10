package com.pragma.usuarios.domain.api;

import com.pragma.usuarios.domain.modelo.Role;

public interface IRolServicePort {
    Role getRole(Long id);
}
