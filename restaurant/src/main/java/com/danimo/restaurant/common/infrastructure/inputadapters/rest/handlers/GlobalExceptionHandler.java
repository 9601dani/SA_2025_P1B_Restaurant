package com.danimo.restaurant.common.infrastructure.inputadapters.rest.handlers;

import com.danimo.restaurant.common.application.exceptions.CategoryAlreadyExistException;
import com.danimo.restaurant.common.application.exceptions.CategoryNotFoundException;
import com.danimo.restaurant.common.infrastructure.inputadapters.rest.dto.RestApiError;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<RestApiError> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestApiError(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<RestApiError> handleCategoryAlreadyExistException(CategoryAlreadyExistException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new RestApiError(HttpStatus.CONFLICT.value(), e.getMessage()));
    }
}
