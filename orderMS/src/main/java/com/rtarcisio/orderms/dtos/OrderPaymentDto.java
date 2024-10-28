package com.rtarcisio.orderms.dtos;

import java.math.BigDecimal;

public class OrderPaymentDto {

    private Long userId;
    private String orderId;
    private String paymentMethod;
    private BigDecimal totalAmount;
}
