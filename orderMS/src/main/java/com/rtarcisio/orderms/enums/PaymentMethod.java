package com.rtarcisio.orderms.enums;



public enum PaymentMethod {
    CREDIT_CARD("CREDIT"),
    PAYPAL("PAYPAL"),
    BOLETO("BOLETO"),
    PIX("PIX");

    private String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
