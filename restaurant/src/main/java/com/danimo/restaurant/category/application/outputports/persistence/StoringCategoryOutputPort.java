package com.danimo.restaurant.category.application.outputports.persistence;

import com.danimo.restaurant.category.domain.Category;

public interface StoringCategoryOutputPort {
    Category save(Category category);
}
