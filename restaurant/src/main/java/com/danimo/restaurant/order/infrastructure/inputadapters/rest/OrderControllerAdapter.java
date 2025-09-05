package com.danimo.restaurant.order.infrastructure.inputadapters.rest;

import com.danimo.restaurant.common.infrastructure.annotations.WebAdapter;
import com.danimo.restaurant.order.application.inputports.*;
import com.danimo.restaurant.order.application.usecases.createorder.CreationOrderDto;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto.CreateOrderRequestDto;
import com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto.OrderResponse;
import com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto.UpdateOrderRquestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Orders", description = "Operaciones realcionadas a las ordenes")
@RestController
@RequestMapping("/v1/orders")
@WebAdapter
public class OrderControllerAdapter {
    private final CreatingOrderInputPort creatingOrderInputPort;
    private final FindingOrderByIdInputPort findingOrderByIdInputPort;
    private final ListingAllOrderInputPort listingAllOrderInputPort;
    private final UpdatingOrderInputPort updatingOrderInputPort;
    private final UpdatingStateOrderInputPort updatingStateOrderInputPort;

    @Autowired
    public OrderControllerAdapter(CreatingOrderInputPort creatingOrderInputPort, FindingOrderByIdInputPort findingOrderByIdInputPort,
                                  ListingAllOrderInputPort listingAllOrderInputPort, UpdatingOrderInputPort updatingOrderInputPort, UpdatingStateOrderInputPort updatingStateOrderInputPort) {
        this.creatingOrderInputPort = creatingOrderInputPort;
        this.findingOrderByIdInputPort = findingOrderByIdInputPort;
        this.listingAllOrderInputPort = listingAllOrderInputPort;
        this.updatingOrderInputPort = updatingOrderInputPort;
        this.updatingStateOrderInputPort = updatingStateOrderInputPort;
    }

    @Operation(
            summary = "Crear nueva orden",
            description = "Devuelve la información de la orden correspondiente al username dado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden creada"),
            @ApiResponse(responseCode = "404", description = "Orden no creada")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequestDto dto){
        CreationOrderDto objectAdaptedFromRestToDomain = dto.toApplicationDto();

        Order order = creatingOrderInputPort.createOrder(objectAdaptedFromRestToDomain);

        OrderResponse response = OrderResponse.fromDomain(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener las ordenes",
            description = "Devuelve la información de todas las ordenes correspondientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordenes encontradas"),
            @ApiResponse(responseCode = "404", description = "Ordenes no encontradas")
    })
    @GetMapping
    @Transactional
    public ResponseEntity<List<OrderResponse>> getAllOrdes() {
        List<OrderResponse> locations = listingAllOrderInputPort.getAllOrders()
                .stream()
                .map(OrderResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(locations);
    }


    @Operation(
            summary = "Busca la orden",
            description = "Devuelve la orden si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrada"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<OrderResponse> getLocationById(@PathVariable String id) {
        Order order = findingOrderByIdInputPort.findById(id);

        return ResponseEntity.ok(OrderResponse.fromDomain(order));
    }

    @Operation(
            summary = "Editar una orden",
            description = "Devuelve la orden actualizada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada"),
            @ApiResponse(responseCode = "404", description = "Orden no actualizada")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<OrderResponse> updateLocation(@RequestBody UpdateOrderRquestDto dto) {
        Order order = updatingOrderInputPort.updateOrder(dto.toApplicationDto());
        return ResponseEntity.ok(OrderResponse.fromDomain(order));
    }
}
