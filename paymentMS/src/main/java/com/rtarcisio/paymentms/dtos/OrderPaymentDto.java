package com.rtarcisio.paymentms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentDto {

    private Long userId;
    private String orderId;
    private String paymentMethod;
    private BigDecimal totalAmount;
}
