package com.danimo.restaurant.category.application.inputports;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.application.exceptions.CategoryNotFoundException;

public interface FindingCategoryByNameInputPort {
    Category findByName(String name) throws CategoryNotFoundException;
}
