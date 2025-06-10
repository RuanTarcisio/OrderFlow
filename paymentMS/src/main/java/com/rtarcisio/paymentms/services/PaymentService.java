package com.rtarcisio.paymentms.services;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.dtos.OrderPaymentDto;
import com.rtarcisio.paymentms.dtos.PaymentDto;
import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import com.rtarcisio.paymentms.mappers.PaymentMapper;
import com.rtarcisio.paymentms.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentDto makePayment(OrderPaymentDto orderPaymentDto) {

        Payment payment = PaymentMapper.inputToPayment(orderPaymentDto);
        payment.processPayment();
        payment = paymentRepository.save(payment);

        if(payment.getPaymentMethod() != PaymentMethod.BOLETO && payment.getPaymentStatus() == PaymentStatus.APPROVED) {

            return PaymentDto.builder()
                    .isApproved(true)
                    .description("Approved")
                    .payment_id(payment.getId())
                    .order_id(payment.getOrderId())
                    .transaction_id(payment.getTransactionId())
                    .paymentDate(LocalDateTime.now())
                    .build();
        }
        return null;

    }
}
