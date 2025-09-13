package com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDbEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(columnDefinition = "CHAR(36)")
    private UUID dishId;
    @Column
    private String dishName;
    @Column
    private int quantity;
    @Column
    private BigDecimal unitPrice;
    @Column
    private BigDecimal lineTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderDbEntity order;
}
