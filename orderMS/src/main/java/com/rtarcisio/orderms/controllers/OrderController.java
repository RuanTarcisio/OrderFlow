package com.rtarcisio.orderms.controllers;


import com.rtarcisio.orderms.dtos.OrderDto;
import com.rtarcisio.orderms.dtos.inputs.OrderInput;
import com.rtarcisio.orderms.services.OrderService;
import com.rtarcisio.orderms.services.client.InventoryClient;
import com.rtarcisio.orderms.services.client.domain.SkuSimpleDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final InventoryClient inventoryClient;

   @Transactional
   @PostMapping("/create")
    public ResponseEntity<OrderDto> newOrder(@Valid @RequestBody OrderInput order) {
        OrderDto orderDto = orderService.createOrder(order);
        return ResponseEntity.ok().body(orderDto);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id){
        return null;
    }

//    @GetMapping
    public String teste(@RequestHeader("RTarcisio_Ecommerce-correlation-id")String correlationId){
        logger.debug("fetchInventoryDetails method start" );
        String status = inventoryClient.teste(correlationId);
        logger.debug("fetchInventoryDetails method end" );
        return status;
    }

    @GetMapping
    public ResponseEntity<SkuSimpleDTO> getSkuProduct() throws IOException {
        logger.debug("fetchInventoryDetails method start" );
         SkuSimpleDTO clientSkuProduct = inventoryClient.getSkuProduct("1bee9754-c309-4d0a-af5e-62663d0fa938").getBody();
        logger.debug("fetchInventoryDetails method end" );

        return ResponseEntity.ok().body(clientSkuProduct);
    }






}
