package com.danimo.restaurant.category.application.inputports;

import com.danimo.restaurant.category.application.usecases.createcategory.CreateCategoryDto;
import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.application.exceptions.CategoryAlreadyExistException;

public interface CreatingCategoryInputPort {
    Category create(CreateCategoryDto dto) throws CategoryAlreadyExistException;
}
