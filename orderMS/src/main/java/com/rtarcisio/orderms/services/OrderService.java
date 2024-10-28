package com.rtarcisio.orderms.services;

import com.rtarcisio.orderms.domains.Order;
import com.rtarcisio.orderms.dtos.OrderPaymentDto;
import com.rtarcisio.orderms.dtos.UpdatePaymentDto;
import com.rtarcisio.orderms.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final StreamBridge streamBridge;
    private final OrderRepository orderRepository;

    public OrderService(StreamBridge streamBridge, OrderRepository orderRepository) {
        this.streamBridge = streamBridge;
        this.orderRepository = orderRepository;
    }

    public void updatePaymentStatus(UpdatePaymentDto updatePaymentDto) {
    }

    private void sendCommunication(Order order) {
        var orderMsgDto = new OrderPaymentDto();
        log.info("Sending Communication request for the details: {}", orderMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", orderMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }
}
