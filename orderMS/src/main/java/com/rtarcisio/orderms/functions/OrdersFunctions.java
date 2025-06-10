package com.rtarcisio.orderms.functions;

import com.rtarcisio.orderms.dtos.OrderPaymentDto;
import com.rtarcisio.orderms.dtos.UpdatePaymentDto;
import com.rtarcisio.orderms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

    @Configuration
    public class OrdersFunctions {

        private static final Logger log = LoggerFactory.getLogger(OrdersFunctions.class);

        @Bean
        public Function<OrderPaymentDto,OrderPaymentDto> orderPayment() {
            return orderPaymentDto -> {
                log.info("Sending sms with the details : " +  orderPaymentDto.toString());
                return orderPaymentDto;
            };
        }

        @Bean
        public Consumer<UpdatePaymentDto> handlePaymentResult(OrderService orderService) {
            return updatePaymentDto -> {
                log.info("Updating Communication status for the account number : " + updatePaymentDto.toString());
                orderService.updatePaymentStatus(updatePaymentDto);
            };
        }

        // IF PAYMENTMETHOD = CREDIT DEVE REALIZAR 3 TENTATIVAS, PODE USAR A API JOB.

    }
