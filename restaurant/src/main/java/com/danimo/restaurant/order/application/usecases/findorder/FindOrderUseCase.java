package com.danimo.restaurant.order.application.usecases.findorder;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.OrderNotFoundException;
import com.danimo.restaurant.order.application.inputports.FindingOrderByIdInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;

import java.util.UUID;

@UseCase
public class FindOrderUseCase implements FindingOrderByIdInputPort {
    private final FindingOrderByIdOutputPort findingOrderByIdOutputPort;
    public FindOrderUseCase(FindingOrderByIdOutputPort findingOrderByIdOutputPort) {
        this.findingOrderByIdOutputPort = findingOrderByIdOutputPort;
    }

    @Override
    public Order findById(String id) {
        return this.findingOrderByIdOutputPort.findById(UUID.fromString(id))
                .orElseThrow(() -> new OrderNotFoundException("No se encontro la orden"));
    }
}
