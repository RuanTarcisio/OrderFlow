package com.rtarcisio.paymentms.controllers;

import com.rtarcisio.paymentms.dtos.OrderPaymentDto;
import com.rtarcisio.paymentms.dtos.PaymentDto;
import com.rtarcisio.paymentms.dtos.PaymentOrderInput;
import com.rtarcisio.paymentms.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> processPayment(@Valid @RequestBody OrderPaymentDto orderPaymentDto) {

        PaymentDto dto = paymentService.makePayment(orderPaymentDto); // Supondo que isso retorna um PaymentDto válido

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getPayment_id()).toUri(); // Use o ID real do pagamento
        return ResponseEntity.created(uri).body(dto); // Retorna 201 Created com o DTO no corpo
    }
}
