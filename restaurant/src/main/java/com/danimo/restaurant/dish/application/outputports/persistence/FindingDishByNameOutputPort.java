package com.danimo.restaurant.dish.application.outputports.persistence;

import com.danimo.restaurant.dish.domain.Dish;

import java.util.Optional;

public interface FindingDishByNameOutputPort {
    Optional<Dish> findByName(String name);
}
