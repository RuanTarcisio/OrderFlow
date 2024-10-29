package com.rtarcisio.paymentms.enums;

import com.rtarcisio.paymentms.strategy.*;

public enum PaymentMethod {
    CREDIT_CARD(new CreditCardPaymentStrategy()),
    PAYPAL(new PayPalPaymentStrategy()),
    BOLETO(new BoletoPaymentStrategy()),
    PIX(new PixPaymentStrategy());

    private final PaymentStrategy strategy;

    PaymentMethod(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }
}
