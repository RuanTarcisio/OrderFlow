package com.rtarcisio.orderms.services.client;

import com.rtarcisio.orderms.services.client.domain.SkuSimpleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    @Override
    public ResponseEntity<SkuSimpleDTO> getSkuProduct(String id) throws IOException {
        return ResponseEntity.ok().body(new SkuSimpleDTO());
    }
}
