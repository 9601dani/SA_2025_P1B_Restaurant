package com.danimo.restaurant.common.application.exceptions;

public class OrderAlreadyCompletedException extends RuntimeException {
    public OrderAlreadyCompletedException(String message) {
        super(message);
    }
}
