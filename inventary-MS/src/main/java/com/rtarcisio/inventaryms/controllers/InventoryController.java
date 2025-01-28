package com.rtarcisio.inventaryms.controllers;

import com.rtarcisio.inventaryms.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    private final InventoryService inventoryService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<Boolean> hasProductInStock(@PathVariable Long idProduct){
        Boolean inStock = inventoryService.hasProductInStock(idProduct);
        return ResponseEntity.ok(inStock);
    }

    @GetMapping
    public String teste(@RequestHeader("RTarcisio_Ecommerce-correlation-id")String correlationId){
        logger.debug("Rtarcisio-correlation-id found: {}", correlationId );
        return "OK!";
    }
}
