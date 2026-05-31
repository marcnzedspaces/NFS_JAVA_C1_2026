package com.example.demoapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties.Http;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(
        RuntimeException exception,
        HttpServletRequest request) {
            logger.warn("Runtine exception at {}: {}", request.getRequestURI(), exception.getMessage());

            ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR", 
                exception.getMessage(), 
                request.getRequestURI()
            );
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);   
    } 

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatusException(
        ResponseStatusException exception,
        HttpServletRequest request) {
            HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
            String error = status.getReasonPhrase().toUpperCase();
            String message = exception.getReason();
            
            logger.warn("HTTP {} at {}: {}", status.value(), request.getRequestURI(), message);

            ApiErrorResponse response = new ApiErrorResponse(
                status.value(),
                error, 
                message, 
                request.getRequestURI()
            );
            
            return ResponseEntity.status(status).body(response);   
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(
        ResponseStatusException exception,
        HttpServletRequest request
    ) {
            logger.warn("Access denied at {} at {}: {}", request.getRequestURI(), exception.getMessage());

            ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "FORBIDDEN", 
                "You do not have permission to access this resource", 
                request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);       
        }
        

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(
        Exception exception,
        HttpServletRequest request
    ) {
            logger.warn("HTTP {} at {}: {}", request.getRequestURI(), exception);

            ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "Something went wrong. Please contact support.",
                request.getRequestURI()
            );
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);   
    }
}
