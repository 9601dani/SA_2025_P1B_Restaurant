package com.danimo.restaurant.order.application.inputports;

import com.danimo.restaurant.order.domain.aggregate.Order;

import java.util.List;

public interface ListingAllOrderInputPort {
    List<Order> getAllOrders();
}
