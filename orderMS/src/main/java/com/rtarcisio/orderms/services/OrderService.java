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
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final StreamBridge streamBridge;
    private final OrderRepository orderRepository;
//    private final PaymentClient paymentClient;

    @Transactional
    public OrderDto createOrder(OrderInput input) {

        Order order = OrderMapper.mapToOrder(input);
        order.setState(OrderState.PENDING_PAYMENT);
        order.setPaymentMethod(PaymentMethod.valueOf(input.getPaymentMethod().toUpperCase()));
        order.setPaymentStatus(PaymentStatus.PENDING);
        order = orderRepository.save(order);

        OrderPaymentDto orderPaymentDto = OrderPaymentDto.builder()
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod().getPaymentMethod())
                .orderId(order.getId())
                .userId(order.getUserId())
                .build();
        sendCommunicationPayment(order);

        //PaymentDto paymentDto = paymentClient.processPayment(orderPaymentDto).getBody();
//        order.setPaymentId(paymentDto.getPayment_id());
//        if (paymentDto.getIsApproved() == true) {
//            order.setPaymentStatus(PaymentStatus.COMPLETED);
//            order.nextState();
//        }

        return OrderMapper.mapToDto(order);
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ObjetoNaoEncontradoException("Order not found by ID"));
    }

    public void updatePaymentStatus(UpdatePaymentDto updatePaymentDto) {

        if (updatePaymentDto.getIsApproved()) {
            Order order = findOrderById(updatePaymentDto.getOrder_id());
            order.setPaymentId(updatePaymentDto.getPayment_id());
            order.setPaymentStatus(PaymentStatus.COMPLETED);
            order.nextState();
            orderRepository.save(order);
        }

    }

    private void sendCommunicationPayment(Order order) {
        var orderPaymentDto = createOrderPayment(order);
        log.info("Sending Communication request for the details: {}", orderPaymentDto);
        var result = streamBridge.send("requestPayment-out-0", orderPaymentDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }

    private OrderPaymentDto createOrderPayment(Order order) {
        return OrderPaymentDto.builder().orderId(order.getId()).userId(order.getUserId()).paymentMethod(order.getPaymentMethod().getPaymentMethod()).totalAmount(order.getTotalAmount()).build();
    }


}
