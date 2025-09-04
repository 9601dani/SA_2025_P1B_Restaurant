package com.danimo.restaurant.category.application.outputports.persistence;

import com.danimo.restaurant.category.domain.Category;

import java.util.Optional;

public interface FindingCategoryByNameOutputPort {
    Optional<Category> findByName(String name);
}
