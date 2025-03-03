package com.rtarcisio.orderms.services.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryFallback implements InventoryClient{
    @Override
    public ResponseEntity<Boolean> hasProductInStock(Long idProduct) {
        return null;
    }

    @Override
    public String teste(String correlationId) {
        return "";
    }
}
