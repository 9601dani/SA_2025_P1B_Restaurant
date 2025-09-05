package com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.repository;

import com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.entity.DishDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DishDbEntityJpaRepository extends JpaRepository<DishDbEntity, UUID> {
    Optional<DishDbEntity> findByName(String name);
}
