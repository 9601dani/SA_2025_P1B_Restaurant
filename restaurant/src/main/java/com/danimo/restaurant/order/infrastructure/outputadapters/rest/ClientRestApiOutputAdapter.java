package com.danimo.restaurant.order.infrastructure.outputadapters.rest;

import com.danimo.restaurant.order.application.outputports.rest.ExistClientOutputPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ClientRestApiOutputAdapter implements ExistClientOutputPort {
    private final RestClient clientRestClient;

    public ClientRestApiOutputAdapter(@Qualifier("ClientRestApi") RestClient clientRestClient) {
        this.clientRestClient = clientRestClient;
    }

    @Override
    public boolean existClient(String nit) {
        return true;
        /*TODO: CAMBIAR POR EL METODO*/
    }
}
