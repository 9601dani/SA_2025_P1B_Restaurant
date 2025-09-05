package com.danimo.restaurant.order.application.usecases.listorder;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.order.application.inputports.ListingAllOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingAllOrdersOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class ListingAllOrdersUseCase implements ListingAllOrderInputPort {
    private final FindingAllOrdersOutputPort findingAllOrdersOutputPort;

    @Autowired
    public ListingAllOrdersUseCase(FindingAllOrdersOutputPort findingAllOrdersOutputPort) {
        this.findingAllOrdersOutputPort = findingAllOrdersOutputPort;
    }


    @Override
    public List<Order> getAllOrders() {
        return this.findingAllOrdersOutputPort.findingAllOrders();
    }
}
