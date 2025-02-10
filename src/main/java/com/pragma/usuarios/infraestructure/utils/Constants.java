package com.pragma.usuarios.infraestructure.utils;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public final class Constants {

    public static final SecretKey SECRET_KEY= Jwts.SIG.HS256.key().build();
    public static final String OK = "User created successfully";
    public static final String ACCESS_DENIED = "Access denied. You do not have the necessary permissions to access this feature";
    public static final String AUTHENTICATION_EXCEPTION = "Authentication failed. Please verify your credentials.";
    public static final String INVALID_PARAMS = "Invalid argument. Please verify the data you are sending.";
    public static final String USER_EXISTS_MESSAGE = "A user is already registered with this email.";
    public static final String USER_AGE_MESSAGE = "The user is not of legal age.";
    public static final String ROL_NOT_MESSAGE = "The rol not found.";
    public static final String FORMAT_DATE_MESSAGE = "Format Date Invalid";
    public static final String JWT_EXCEPTION = "Error con el JWT.";
    public static final String JWT_EXPIRED = "The JWT has expired.";
    public static final String USER_NOT_MESSAGE = "User not found.";
    public static final String FILTER_ACCESS_MESSAGE="No authentication credentials found. Please log in to access this feature";
}
