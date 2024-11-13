package com.rtarcisio.orderms.dtos.inputs;

import com.rtarcisio.orderms.dtos.ProductDto;
import com.rtarcisio.orderms.enums.PaymentMethod;
import com.rtarcisio.orderms.validations.OrderInputInsert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@OrderInputInsert
public class OrderInput {
    private List<ProductDto> products;
    private Long id_user;
    private String paymentMethod;

}
