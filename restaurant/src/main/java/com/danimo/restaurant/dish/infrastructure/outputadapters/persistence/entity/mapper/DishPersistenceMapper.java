package com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.entity.mapper;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.dish.domain.*;
import com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.entity.DishDbEntity;
import org.springframework.stereotype.Component;

@Component
public class DishPersistenceMapper {
    public Dish toDomain(DishDbEntity dbEntity){
        if(dbEntity == null) return null;

        return new Dish(
                DishId.fromUuid(dbEntity.getId()),
                dbEntity.getName(),
                dbEntity.getDescription(),
                DishPrice.fromBigDecimal(dbEntity.getPrice()),
                new Category(dbEntity.getCategory()),
                DishCreatedAt.fromDomain(dbEntity.getCreatedAt()),
                DishUpdatedAt.fromDomain(dbEntity.getUpdatedAt())
        );
    }

    public DishDbEntity toDbEntity(Dish dish){
        if(dish == null) return null;

        return new DishDbEntity(
                dish.getId().getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice().getPrice(),
                dish.getCategory().getName(),
                dish.getCreatedAt().getCreatedAt(),
                dish.getUpdatedAt().getUpdatedAt()
        );
    }
}
