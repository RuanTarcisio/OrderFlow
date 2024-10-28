package com.rtarcisio.paymentms.functions;

import com.rtarcisio.paymentms.dtos.OrderPaymentDto;
import com.rtarcisio.paymentms.dtos.UpdatePaymentDto;
import com.rtarcisio.paymentms.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class PaymentsFunctions {

    private static final Logger log = LoggerFactory.getLogger(PaymentsFunctions.class);

    private final PaymentService paymentService;

    @Autowired
    public PaymentsFunctions(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Bean
    public Function<OrderPaymentDto, UpdatePaymentDto> updatePayment() {
        return orderPaymentDto -> {
            log.info("Sending email with the details : " +  orderPaymentDto.toString());
            return paymentService.makePayment(orderPaymentDto);
        };
    }

}
