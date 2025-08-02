/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.security;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author javaugi
 */
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.FORBIDDEN, new ErrorResponseException(HttpStatus.FORBIDDEN));
        //ErrorResponse errorResponse = new ErrorResponse("Access Denied", "You don't have permission to access this resource",  HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        //ErrorResponse errorResponse = new ErrorResponse("Authentication Failed", ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}