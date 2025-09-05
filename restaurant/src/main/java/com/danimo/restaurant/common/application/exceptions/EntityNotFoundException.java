package com.danimo.restaurant.common.application.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }
}
