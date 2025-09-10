package com.danimo.restaurant.order.infrastructure.outputadapters.rest;

import com.danimo.restaurant.order.application.outputports.rest.CreateMovementOutputPort;
import com.danimo.restaurant.order.infrastructure.outputadapters.rest.dto.CreateBillRequestDto;
import com.danimo.restaurant.order.infrastructure.outputadapters.rest.dto.CreateMovementRequestDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class MovementRestApiOutputAdapter implements CreateMovementOutputPort {
    private final RestClient movementRestClient;

    public MovementRestApiOutputAdapter(@Qualifier("MovementRestApi") RestClient movementRestClient) {
        this.movementRestClient = movementRestClient;
    }

    @Override
    public boolean isSuccess(CreateMovementRequestDto dto) {
        try {
            var response = movementRestClient.post()
                    .uri("")
                    .body(dto)
                    .retrieve()
                    .toBodilessEntity();

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
