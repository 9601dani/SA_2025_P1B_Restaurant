package com.danimo.restaurant.dish.application.outputports.persistence;

import com.danimo.restaurant.dish.domain.Dish;

import java.util.Optional;
import java.util.UUID;

public interface FindingDishByIdOutputPort {
    Optional<Dish> findById(UUID id);
}
