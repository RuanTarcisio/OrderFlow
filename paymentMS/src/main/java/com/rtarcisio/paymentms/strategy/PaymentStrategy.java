package com.rtarcisio.paymentms.strategy;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.enums.PaymentMethod;

public interface PaymentStrategy {
    void processPayment(Payment payment);
    PaymentMethod getPaymentMethodType();

}
