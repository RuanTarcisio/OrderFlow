package com.rtarcisio.orderms.controllers;


import com.rtarcisio.orderms.dtos.OrderDto;
import com.rtarcisio.orderms.dtos.inputs.OrderInput;
import com.rtarcisio.orderms.services.OrderService;
import com.rtarcisio.orderms.services.client.InventoryClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final InventoryClient inventoryClient;

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

    @GetMapping
    public String teste(@RequestHeader("RTarcisio_Ecommerce-correlation-id")String correlationId){
        logger.debug("RTarcisio_Ecommerce-correlation-id found: {}", correlationId );
        String status = inventoryClient.teste(correlationId);
        return status;
    }
}
