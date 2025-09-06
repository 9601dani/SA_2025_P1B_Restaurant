package com.danimo.restaurant.order.application.usecases.updateorder;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.EntityNotFoundException;
import com.danimo.restaurant.common.application.exceptions.OrderAlreadyCompletedException;
import com.danimo.restaurant.common.application.exceptions.OrderNotFoundException;
import com.danimo.restaurant.order.application.inputports.UpdatingOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.ExistClientOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.ExistEmployeeOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.ExistLocationOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateOrderUseCase implements UpdatingOrderInputPort {
    private final FindingOrderByIdOutputPort findingOrderByIdOutputPort;
    private final StoringOrderOutputPort storingOrderOutputPort;
    private final ExistLocationOutputPort existLocationOutputPort;
    private final ExistEmployeeOutputPort existEmployeeOutputPort;
    private final ExistClientOutputPort existClientOutputPort;

    @Autowired
    public UpdateOrderUseCase(FindingOrderByIdOutputPort findingOrderByIdOutputPort,
                              StoringOrderOutputPort storingOrderOutputPort, ExistLocationOutputPort existLocationOutputPort,
                              ExistEmployeeOutputPort existEmployeeOutputPort, ExistClientOutputPort existClientOutputPort) {
        this.findingOrderByIdOutputPort = findingOrderByIdOutputPort;
        this.storingOrderOutputPort = storingOrderOutputPort;
        this.existLocationOutputPort = existLocationOutputPort;
        this.existEmployeeOutputPort = existEmployeeOutputPort;
        this.existClientOutputPort = existClientOutputPort;
    }


    @Override
    public Order updateOrder(UpdateOrderDto dto) throws OrderNotFoundException {
        Order current = findingOrderByIdOutputPort.findById(dto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("La orden no existe"));

        if(current.getStatus().equals(OrderStatus.CANCELLED) || current.getStatus().equals(OrderStatus.COMPLETED) ||
                current.getStatus().equals(OrderStatus.NOT_AUTHORIZED)) {
            throw new OrderAlreadyCompletedException("La orden ya no puede ser modificada");
        }

        if(!existLocationOutputPort.existLocation(dto.getLocationId())) {
            throw new EntityNotFoundException("El establecimiento no existe");
        }

        if (dto.getNit() != null && !dto.getNit().equals("CF")) {
            if (!existClientOutputPort.existClient(dto.getNit())) {
                throw new EntityNotFoundException("El cliente no existe");
            }
        }

        if(!existEmployeeOutputPort.existEmployee(dto.getUserEmployeeId())){
            throw new EntityNotFoundException("El empleado no existe");
        }

        Order updated = dto.applyChanges(current);

        updated.recalculateTotals();

        return storingOrderOutputPort.save(updated);
    }
}
