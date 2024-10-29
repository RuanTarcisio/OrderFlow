package com.rtarcisio.orderms.dtos;

import com.rtarcisio.orderms.domains.ProductOrder;
import com.rtarcisio.orderms.enums.PaymentMethod;
import com.rtarcisio.orderms.enums.PaymentStatus;
import com.rtarcisio.orderms.state.OrderState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDto> products = new ArrayList<>(); // Lista dos produtos e seus dados imutáveis

    private BigDecimal totalAmount;

    private String paymentMethod;

    private String paymentStatus;

    private Long paymentId;

    private String state;
}
