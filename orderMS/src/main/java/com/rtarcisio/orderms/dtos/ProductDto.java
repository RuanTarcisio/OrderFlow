package com.rtarcisio.orderms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id_product;
    private String name;
    private BigDecimal price;
    private String category;
    private Integer quantity;
}
