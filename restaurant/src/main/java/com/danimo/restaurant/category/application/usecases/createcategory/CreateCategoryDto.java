package com.danimo.restaurant.category.application.usecases.createcategory;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.category.domain.CategoryCreatedAt;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateCategoryDto {
    private String name;
    private CategoryCreatedAt categoryCreatedAt;

    public Category toDomain(){
        return new Category(name.toUpperCase(), categoryCreatedAt);
    }
}
