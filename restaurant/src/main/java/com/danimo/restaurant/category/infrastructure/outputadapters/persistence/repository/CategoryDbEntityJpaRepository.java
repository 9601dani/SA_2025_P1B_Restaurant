package com.danimo.restaurant.category.infrastructure.outputadapters.persistence.repository;

import com.danimo.restaurant.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDbEntityJpaRepository extends JpaRepository<CategoryDbEntity, String> {
}
