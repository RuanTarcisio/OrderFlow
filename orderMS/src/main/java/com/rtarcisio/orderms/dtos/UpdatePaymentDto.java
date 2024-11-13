package com.rtarcisio.orderms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdatePaymentDto {
    private Long payment_id;
    private String order_id;
    private Boolean isApproved;
    private LocalDateTime paymentDate;
    private String description;
}
