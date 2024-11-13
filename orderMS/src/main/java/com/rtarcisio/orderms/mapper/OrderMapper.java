package com.rtarcisio.orderms.mapper;

import com.rtarcisio.orderms.domains.Order;
import com.rtarcisio.orderms.domains.ProductOrder;
import com.rtarcisio.orderms.dtos.OrderDto;
import com.rtarcisio.orderms.dtos.ProductDto;
import com.rtarcisio.orderms.dtos.inputs.OrderInput;
import com.rtarcisio.orderms.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(OrderInput input){

        List<ProductOrder> productOrders = ProductMapper.mapToListProductOrder(input.getProducts());

        return Order.builder()
                .userId(input.getId_user())
                .products(productOrders)
                .totalAmount(totalValue(productOrders))
                .paymentMethod(PaymentMethod.valueOf(input.getPaymentMethod().toUpperCase()))
                .build();
    }
    public static OrderDto mapToDto(Order order) {
        List<ProductDto> productsDto = ProductMapper.mapToListProductDto(order.getProducts());

        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .products(productsDto)
                .state(order.getStatus())
                .paymentStatus(order.getPaymentStatus().name())
                .paymentMethod(order.getPaymentMethod().name())
                .paymentId(order.getPaymentId())
                .build();
    }

    private static BigDecimal totalValue (List<ProductOrder> list){
        return list.stream()
                .map(productOrder -> productOrder.getPriceAtOrder())
                .reduce(BigDecimal.ZERO, (bigDecimal, price) -> bigDecimal.add(price));
    }



}

