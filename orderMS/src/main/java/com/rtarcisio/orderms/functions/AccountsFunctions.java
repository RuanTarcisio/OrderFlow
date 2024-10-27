package com.rtarcisio.orderms.functions;

import com.rtarcisio.orderms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<String> updateCommunication(OrderService orderService) {
        return paymentNumber -> {
            log.info("Updating Communication status for the account number : " + paymentNumber.toString());
            orderService.updatePaymentStatus(paymentNumber);
        };
    }

}
