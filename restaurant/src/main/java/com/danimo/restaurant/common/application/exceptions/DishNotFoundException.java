package com.danimo.restaurant.common.application.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
