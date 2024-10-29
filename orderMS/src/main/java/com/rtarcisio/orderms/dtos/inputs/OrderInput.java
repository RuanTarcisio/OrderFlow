package com.rtarcisio.orderms.dtos.inputs;

import com.rtarcisio.orderms.dtos.ProductDto;
import com.rtarcisio.orderms.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInput {
    private List<ProductDto> products;
    private Long id_user;
    private String paymentMethod;

}
