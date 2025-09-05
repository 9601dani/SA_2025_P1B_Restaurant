package com.danimo.restaurant.order.application.usecases.updateorder;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.EntityNotFoundException;
import com.danimo.restaurant.common.application.exceptions.OrderAlreadyCompletedException;
import com.danimo.restaurant.common.application.exceptions.OrderNotFoundException;
import com.danimo.restaurant.order.application.inputports.UpdatingOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateOrderUseCase implements UpdatingOrderInputPort {
    private final FindingOrderByIdOutputPort findingOrderByIdOutputPort;
    private final StoringOrderOutputPort storingOrderOutputPort;

    @Autowired
    public UpdateOrderUseCase(FindingOrderByIdOutputPort findingOrderByIdOutputPort,
                              StoringOrderOutputPort storingOrderOutputPort) {
        this.findingOrderByIdOutputPort = findingOrderByIdOutputPort;
        this.storingOrderOutputPort = storingOrderOutputPort;
    }


    @Override
    public Order updateOrder(UpdateOrderDto dto) throws OrderNotFoundException {
        Order current = findingOrderByIdOutputPort.findById(dto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("La orden no existe"));

        if(current.getStatus().equals(OrderStatus.CANCELLED) || current.getStatus().equals(OrderStatus.COMPLETED) ||
                current.getStatus().equals(OrderStatus.NOT_AUTHORIZED)) {
            throw new OrderAlreadyCompletedException("La orden ya no puede ser modificada");
        }

        Order updated = dto.applyChanges(current);

        updated.recalculateTotals();

        return storingOrderOutputPort.save(updated);
    }
}
