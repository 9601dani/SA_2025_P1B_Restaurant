package com.danimo.restaurant.common.application.exceptions;

public class DishAlreadyExistException extends RuntimeException {
    public DishAlreadyExistException(String message) {
        super(message);
    }
}
