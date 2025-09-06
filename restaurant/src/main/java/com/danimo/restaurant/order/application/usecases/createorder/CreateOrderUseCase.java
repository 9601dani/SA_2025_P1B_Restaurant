package com.danimo.restaurant.order.application.usecases.createorder;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.EntityNotFoundException;
import com.danimo.restaurant.order.application.inputports.CreatingOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.ExistClientOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.ExistEmployeeOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.ExistLocationOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateOrderUseCase implements CreatingOrderInputPort {

    private final StoringOrderOutputPort storingOrderOutputPort;
    private final ExistLocationOutputPort existLocationOutputPort;
    private final ExistEmployeeOutputPort existEmployeeOutputPort;
    private final ExistClientOutputPort existClientOutputPort;

    @Autowired
    public CreateOrderUseCase (StoringOrderOutputPort storingOrderOutputPort,
                               ExistLocationOutputPort existLocationOutputPort, ExistEmployeeOutputPort existEmployeeOutputPort,
                               ExistClientOutputPort existClientOutputPort) {
        this.storingOrderOutputPort = storingOrderOutputPort;
        this.existLocationOutputPort = existLocationOutputPort;
        this.existEmployeeOutputPort = existEmployeeOutputPort;
        this.existClientOutputPort = existClientOutputPort;
    }

    @Override
    public Order createOrder(CreationOrderDto creationOrderDto) {
        if(!existLocationOutputPort.existLocation(creationOrderDto.getLocationId())) {
            throw new EntityNotFoundException("El establecimiento no existe");
        }

        if (creationOrderDto.getNit() != null && !creationOrderDto.getNit().equals("CF")) {
            if (!existClientOutputPort.existClient(creationOrderDto.getNit())) {
                throw new EntityNotFoundException("El cliente no existe");
            }
        }

        if(!existEmployeeOutputPort.existEmployee(creationOrderDto.getUserEmployeeId())){
            throw new EntityNotFoundException("El empleado no existe");
        }

        Order order = creationOrderDto.toDomain();

        order.recalculateTotals();
        order.changeStatusWhenCreated();

        return storingOrderOutputPort.save(order);
    }
}
