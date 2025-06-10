package com.rtarcisio.orderms.services.client;

import com.rtarcisio.orderms.dtos.OrderPaymentDto;
import com.rtarcisio.orderms.services.client.domain.PaymentDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-ms", path = "payments")
@Component
public interface PaymentClient {

    @PostMapping
    public ResponseEntity<PaymentDto> processPayment(@Valid @RequestBody OrderPaymentDto orderPaymentDto) ;
}
