package com.rtarcisio.orderms.services.client;

import com.rtarcisio.orderms.services.client.domain.SkuSimpleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;

@FeignClient(name = "product-ms", path = "product", fallback = InventoryFallback.class)
@Primary
public interface InventoryClient {

    @GetMapping("/{idProduct}")
    ResponseEntity<Boolean> hasProductInStock(@PathVariable Long idProduct);

    @GetMapping
    public String teste(@RequestHeader("RTarcisio_Ecommerce-correlation-id")String correlationId);

    @GetMapping("/sku/{id}")
    public ResponseEntity<SkuSimpleDTO> getSkuProduct(@PathVariable String id) throws IOException;
}