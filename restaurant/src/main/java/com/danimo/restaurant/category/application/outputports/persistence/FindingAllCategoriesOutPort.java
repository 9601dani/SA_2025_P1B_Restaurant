package com.danimo.restaurant.category.application.outputports.persistence;

import com.danimo.restaurant.category.domain.Category;

import java.util.List;

public interface FindingAllCategoriesOutPort {
    List<Category> findAllCategories();
}
