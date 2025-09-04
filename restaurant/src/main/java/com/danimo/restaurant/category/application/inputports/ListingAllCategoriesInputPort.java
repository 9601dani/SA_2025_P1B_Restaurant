package com.danimo.restaurant.category.application.inputports;

import com.danimo.restaurant.category.domain.Category;

import java.util.List;

public interface ListingAllCategoriesInputPort {
    List<Category> getAllCategories();
}
