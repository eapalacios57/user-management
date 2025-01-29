package com.pragma.usuarios.infraestructure.out.jpa.repository;

import com.pragma.usuarios.infraestructure.out.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
    Optional<RolEntity> findByName(String name);
}
