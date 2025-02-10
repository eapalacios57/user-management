package com.pragma.usuarios.infraestructure.exceptionHandler;

import com.pragma.usuarios.application.dto.ApiError;
import com.pragma.usuarios.infraestructure.exception.*;
import com.pragma.usuarios.infraestructure.utils.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.core.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError<String>> handlerAccessDeniedException(HttpServletRequest request,
                                                          AccessDeniedException exception){

        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setMessage(Constants.ACCESS_DENIED);
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError<String>> handleAuthenticationException(AuthenticationException ex) {

        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage(Constants.AUTHENTICATION_EXCEPTION);
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
          ApiError<Map<String, String>> apiError = new ApiError<>();
          Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
          apiError.setBackendMessage(errors);
          apiError.setUrl("");
          apiError.setMessage(Constants.INVALID_PARAMS);
          apiError.setTimestamp(LocalDateTime.now());
          return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage(Constants.INVALID_PARAMS);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError<String>> userAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(Constants.USER_EXISTS_MESSAGE);
        apiError.setUrl("");
        apiError.setMessage(Constants.USER_EXISTS_MESSAGE);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidBirthDateException.class)
    public ResponseEntity<ApiError<String>> validBirthDateException(ValidBirthDateException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(Constants.USER_AGE_MESSAGE);
        apiError.setUrl("");
        apiError.setMessage(Constants.USER_AGE_MESSAGE);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RolNotFoundException.class)
    public ResponseEntity<ApiError<String>> rolNotFoundException(RolNotFoundException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getMessage() != null ? ex.getMessage() : Constants.ROL_NOT_MESSAGE);
        apiError.setUrl("");
        apiError.setMessage(ex.getMessage() != null ? ex.getMessage() : Constants.ROL_NOT_MESSAGE);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FormatDateInvalidException.class)
    public ResponseEntity<ApiError<String>> formatDateInvalidException(FormatDateInvalidException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(Constants.FORMAT_DATE_MESSAGE);
        apiError.setUrl("");
        apiError.setMessage(Constants.FORMAT_DATE_MESSAGE);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError<String>> handleJwtException(JwtException ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage(Constants.JWT_EXCEPTION);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<ApiError<String>> handleExpiredJwtException(ServletException  ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage(Constants.JWT_EXPIRED);
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError<String>> userNotFoundException(UserNotFoundException  ex) {
        ApiError<String> apiError = new ApiError<>();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl("");
        apiError.setMessage(ex.getLocalizedMessage());
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


}
