package com.pragma.usuarios.infraestructure.out.jpa.repository;

import com.pragma.usuarios.infraestructure.out.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {

}
