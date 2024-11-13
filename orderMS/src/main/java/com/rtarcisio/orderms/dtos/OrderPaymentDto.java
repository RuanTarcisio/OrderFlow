package com.rtarcisio.orderms.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Builder
@Data
public class OrderPaymentDto {

    private Long userId;
    private String orderId;
    private String paymentMethod;
    private BigDecimal totalAmount;
}
