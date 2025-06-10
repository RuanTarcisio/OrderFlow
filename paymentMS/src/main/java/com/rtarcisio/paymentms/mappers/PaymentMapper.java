package com.rtarcisio.paymentms.mappers;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.dtos.OrderPaymentDto;
import com.rtarcisio.paymentms.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {
    public static Payment inputToPayment(OrderPaymentDto dto) {
        return Payment.builder()
                .paymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod().toUpperCase()))
                .userId(dto.getUserId())
                .orderId(dto.getOrderId())
                .amount(dto.getTotalAmount())
                .paymentDate(LocalDateTime.now())
                .build();
    }
}
