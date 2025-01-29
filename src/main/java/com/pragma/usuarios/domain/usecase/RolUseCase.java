package com.pragma.usuarios.domain.usecase;

import com.pragma.usuarios.domain.api.IRolServicePort;
import com.pragma.usuarios.domain.modelo.Role;
import com.pragma.usuarios.domain.spi.IRolPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    @Override
    public Role getRole(String name) {
        return rolPersistencePort.getRole(name);
    }
}
