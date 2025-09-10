package com.danimo.restaurant.order.domain.aggregate;

import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import com.danimo.restaurant.order.domain.entity.Item;
import com.danimo.restaurant.order.domain.vo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@DomainEntity
@AllArgsConstructor
public class Order {
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.12);

    private OrderId id;
    private String description;
    private UUID locationId;
    private String idClient;
    private OrderStatus status;
    private OrderSubtotal subTotal;
    private OrderDiscount discount;
    private OrderTax tax;
    private BigDecimal total;
    private OrderCreatedAt createdAt;
    private OrderUpdatedAt updatedAt;
    private UUID userEmployeeId;
    private List<Item> items;

    private void calculateSubTotal() {
        BigDecimal sum = items.stream()
                .map(item -> item.calculateLineTotal().toBigDecimal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.subTotal = OrderSubtotal.fromBigDecimal(sum);
    }

    private void calculateTax() {
        if(this.subTotal == null) {
            throw new IllegalStateException("El subtotal es nulo, no se puede calcular los impuestos");
        }
        BigDecimal base = this.subTotal.getSubtotal().subtract(this.discount.getDiscount());
        BigDecimal taxValue = base.multiply(TAX_RATE);
        this.tax = OrderTax.fromBigDecimal(taxValue);
    }

    private void calculateTotal() {
        if(this.subTotal == null || this.tax == null) {
            throw new IllegalStateException("Subtotal o tax nulos, no se puede calcular el total");
        }
        this.total = this.subTotal.getSubtotal()
                .subtract(this.discount.getDiscount())
                .add(this.tax.getTax());
    }

    public void recalculateTotals() {
        calculateSubTotal();
        calculateTax();
        calculateTotal();
    }

    public void changeStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("No se puede modificar una orden cancelada");
        }
        this.status = newStatus;
        this.updatedAt = OrderUpdatedAt.generate();
    }

    public void changeStatusWhenCreated(){
        this.status = OrderStatus.IN_PROGRESS;
    }


}
