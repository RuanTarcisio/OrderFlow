package com.rtarcisio.paymentms.functions;

import com.rtarcisio.paymentms.dtos.OrderPaymentDto;
import com.rtarcisio.paymentms.dtos.PaymentDto;
import com.rtarcisio.paymentms.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@RequiredArgsConstructor
@Configuration
public class PaymentsFunctions {

    private static final Logger log = LoggerFactory.getLogger(PaymentsFunctions.class);

    private final PaymentService paymentService;

    @Bean
    public Function<OrderPaymentDto, PaymentDto> processPayment() {
        return orderPaymentDto -> {
            log.info("Payment-MS: Pedido de pagamento recebido: {}", orderPaymentDto);

            return paymentService.makePayment(orderPaymentDto);
        };
    }

}
