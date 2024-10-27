package com.rtarcisio.orderms.dtos.inputs;

import com.rtarcisio.orderms.dtos.ProductDto;

import java.util.List;

public class OrderInput {
    private List<ProductDto> products;
    private Long id_user;
}
