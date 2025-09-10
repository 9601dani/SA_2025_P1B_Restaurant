package com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity;

import com.danimo.restaurant.order.domain.vo.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDbEntity {

    @Id
    private UUID id;
    @Column
    private String description;
    @Column
    private UUID locationId;
    @Column
    private String idClient;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column
    private BigDecimal subTotal;
    @Embedded
    private OrderDiscountEmbeddable discount;
    @Column
    private BigDecimal tax;
    @Column
    private BigDecimal total;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @Column
    private UUID userEmployeeId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemDbEntity> items = new ArrayList<>();
}
