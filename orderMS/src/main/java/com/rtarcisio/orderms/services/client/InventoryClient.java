package com.rtarcisio.orderms.services.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "inventory-ms", path = "inventory")
public interface InventoryClient {

    @GetMapping("/{idProduct}")
    ResponseEntity<Boolean> hasProductInStock(@PathVariable Long idProduct);

    @GetMapping
    public String teste(@RequestHeader("RTarcisio_Ecommerce-correlation-id")String correlationId);
}