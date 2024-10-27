package com.rtarcisio.paymentms.strategy;

import com.rtarcisio.paymentms.domains.Payment;

public interface PaymentStrategy {
    void processPayment(Payment payment);

}
