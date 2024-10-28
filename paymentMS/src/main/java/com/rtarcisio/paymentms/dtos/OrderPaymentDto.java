package com.rtarcisio.paymentms.dtos;

import lombok.Data;

import java.math.BigDecimal;

public record OrderPaymentDto (Long userId, String orderId,
                               String paymentMethod, BigDecimal totalAmount){


}
