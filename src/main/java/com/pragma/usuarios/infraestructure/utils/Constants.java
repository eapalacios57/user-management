package com.pragma.usuarios.infraestructure.utils;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public final class Constants {

    public static final SecretKey SECRET_KEY= Jwts.SIG.HS256.key().build();
    public static final String OK = "User created successfully";
}
