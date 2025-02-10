package com.pragma.usuarios.infraestructure.out.jpa.adapter;

import com.pragma.usuarios.domain.modelo.Role;
import com.pragma.usuarios.domain.spi.IRolPersistencePort;
import com.pragma.usuarios.infraestructure.exception.RolNotFoundException;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.RolEntityMapper;
import com.pragma.usuarios.infraestructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

        private final IRolRepository rolRepository;
        private final  RolEntityMapper rolEntityMapper;

        @Override
        public Role getRole(Long id) {
            return rolEntityMapper.toRole(rolRepository.findById(id).orElseThrow(RolNotFoundException::new));
        }
}
