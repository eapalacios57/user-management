package com.pragma.usuarios.infraestructure.exception;

public class RolNotFoundException extends RuntimeException {
    public RolNotFoundException() {
        super();
    }
    public RolNotFoundException(String message) {
        super(message);
    }
}
