package com.rtarcisio.orderms.services;

import com.rtarcisio.orderms.domains.Order;
import com.rtarcisio.orderms.dtos.OrderDto;
import com.rtarcisio.orderms.dtos.OrderPaymentDto;
import com.rtarcisio.orderms.dtos.UpdatePaymentDto;
import com.rtarcisio.orderms.dtos.inputs.OrderInput;
import com.rtarcisio.orderms.enums.PaymentMethod;
import com.rtarcisio.orderms.enums.PaymentStatus;
import com.rtarcisio.orderms.exceptions.ObjetoNaoEncontradoException;
import com.rtarcisio.orderms.mapper.OrderMapper;
import com.rtarcisio.orderms.repositories.OrderRepository;
import com.rtarcisio.orderms.state.OrderState;
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

    public OrderDto createOrder(OrderInput input) {

        Order order = OrderMapper.mapToOrder(input);
        order.setState(OrderState.PENDING_PAYMENT);
        order.setPaymentMethod(PaymentMethod.valueOf(input.getPaymentMethod().toUpperCase()));
        order.setPaymentStatus(PaymentStatus.PENDING);
        orderRepository.save(order);
        sendCommunication(order);

        return OrderMapper.mapToDto(order);
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ObjetoNaoEncontradoException("Order not found by ID"));
    }

    public void updatePaymentStatus(UpdatePaymentDto updatePaymentDto) {
        Order order = findOrderById(updatePaymentDto.getOrder_id());

        if (updatePaymentDto.getIsApproved()) {
            order.setPaymentId(updatePaymentDto.getPayment_id());
            order.setPaymentStatus(PaymentStatus.COMPLETED);
            order.setPaymentApproved(updatePaymentDto.getIsApproved());
            order.nextState();
        }

    }

    private void sendCommunication(Order order) {
        var orderMsgDto = createOrderPayment(order);
        log.info("Sending Communication request for the details: {}", orderMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", orderMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }

    private OrderPaymentDto createOrderPayment(Order order) {
        return OrderPaymentDto.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .paymentMethod(order.getPaymentMethod().getPaymentMethod())
                .totalAmount(order.getTotalAmount())
                .build();
    }

}
