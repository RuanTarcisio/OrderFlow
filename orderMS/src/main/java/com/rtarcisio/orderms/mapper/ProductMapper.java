package com.rtarcisio.orderms.mapper;

import com.rtarcisio.orderms.domains.ProductOrder;
import com.rtarcisio.orderms.dtos.ProductDto;

import java.util.List;

public class ProductMapper {

    public static ProductDto mapToProductDto(ProductOrder productOrder){
        return ProductDto.builder()
                .id_product(productOrder.getProductId())
                .quantity(productOrder.getQuantity())
                .category(productOrder.getCategory())
                .name(productOrder.getName())
                .price(productOrder.getPriceAtOrder())
                .build();
    }

    public static ProductOrder mapToProductOrder(ProductDto productDto){
        return ProductOrder.builder()
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .category(productDto.getCategory())
                .productId(productDto.getId_product())
                .priceAtOrder(productDto.getPrice())
                .build();
    }

    public static List<ProductDto> mapToListProductDto(List<ProductOrder> products){
        return products.stream()
                .map(ProductMapper::mapToProductDto)
                .toList();
    }

    public static List<ProductOrder> mapToListProductOrder(List<ProductDto> dtos){
        return dtos.stream()
                .map(ProductMapper::mapToProductOrder    /*{
                     Mapeia cada ProductDto para um ProductOrder
                    ProductOrder productOrder = new ProductOrder();
                    productOrder.setProductId(productDto.getId_product());
                    productOrder.setName(productDto.getName());  // Nome do produto
                    productOrder.setCategory(productDto.getCategory());
                    productOrder.setPriceAtOrder(productDto.getPrice());
                    productOrder.setQuantity(productDto.getQuantity());

                    return productOrder;
                }*/)
                .toList();
    }

}
