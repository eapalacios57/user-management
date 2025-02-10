package com.pragma.usuarios.domain.modelo;

public enum EnumRol {
    ADMIN(1L, "ADMIN"),
    OWNER(2L, "OWNER"),
    EMPLOYEE(3L, "EMPLOYEE"),
    CLIENT(4L, "CLIENT");

    private final Long id;
    private final String rol;

    EnumRol(Long id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public String getRol() {
        return this.rol;
    }

    public Long getId() {
        return id;
    }
}
