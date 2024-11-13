package com.rtarcisio.paymentms.services;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.dtos.OrderPaymentDto;
import com.rtarcisio.paymentms.dtos.UpdatePaymentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    public UpdatePaymentDto makePayment(OrderPaymentDto orderPaymentDto) {
        Payment payment = new Payment();


        return UpdatePaymentDto.builder()
                .isApproved(true)
                .description("Approved")
                .payment_id(1L)
                .order_id(orderPaymentDto.orderId())
                .paymentDate(LocalDateTime.now())
                .build();
//        updatePaymentDto.

    }
}
