package com.rtarcisio.paymentms.enums;

import com.rtarcisio.paymentms.strategy.CreditCardPaymentStrategy;
import com.rtarcisio.paymentms.strategy.PayPalPaymentStrategy;
import com.rtarcisio.paymentms.strategy.PaymentStrategy;
import com.rtarcisio.paymentms.strategy.PixPaymentStrategy;

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
