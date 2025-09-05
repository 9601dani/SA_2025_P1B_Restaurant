package com.danimo.restaurant.order.infrastructure.outputadapters.persistence.repository;

import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.OrderDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDbEntityJpaRepository extends JpaRepository<OrderDbEntity, UUID> {
}
