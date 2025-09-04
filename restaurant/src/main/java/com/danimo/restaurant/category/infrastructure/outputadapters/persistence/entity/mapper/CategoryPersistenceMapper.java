package com.danimo.restaurant.category.infrastructure.outputadapters.persistence.entity.mapper;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.category.domain.CategoryCreatedAt;
import com.danimo.restaurant.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryPersistenceMapper {
    public Category toDomain(CategoryDbEntity dbEntity){
        if(dbEntity == null) return null;

        return new Category(
                dbEntity.getName(),
                CategoryCreatedAt.fromDomain(dbEntity.getCreatedAt())
        );
    }

    public CategoryDbEntity toDbEntity(Category category){
        if(category == null) return null;

        return new CategoryDbEntity(
                category.getName(),
                category.getCreatedAt().getCreatedAt()
        );
    }
}
