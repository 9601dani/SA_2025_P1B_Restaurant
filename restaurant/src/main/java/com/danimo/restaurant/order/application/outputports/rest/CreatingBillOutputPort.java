package com.danimo.restaurant.order.application.outputports.rest;

import com.danimo.restaurant.order.domain.aggregate.Order;

public interface CreatingBillOutputPort {
    boolean createBill(Order order);
}
