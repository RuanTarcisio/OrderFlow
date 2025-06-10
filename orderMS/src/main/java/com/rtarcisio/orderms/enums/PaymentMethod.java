package com.rtarcisio.orderms.enums;



public enum PaymentMethod {
    CREDIT_CARD("CREDIT_CARD"),
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
