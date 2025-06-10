package com.rtarcisio.paymentms.dtos;

import java.math.BigDecimal;

public class PaymentOrderInput {
    private String orderId;
    private String paymentMethod;
    private BigDecimal amount;
}
