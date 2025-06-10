package com.rtarcisio.paymentms.strategy;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class PayPalPaymentStrategy implements PaymentStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PayPalPaymentStrategy.class);
    private final Random random = new Random();

    @Override
    public void processPayment(Payment payment) {
        logger.info("Iniciando processamento de pagamento PayPal para o pedido: {}", payment.getOrderId());

        // Simulação: 70% de chance de sucesso para PayPal
        boolean success = random.nextDouble() < 0.7;

        if (success) {
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setUpdatedDate(LocalDateTime.now());
            payment.setTransactionId(UUID.randomUUID().toString()); // Simula ID da transação PayPal
            logger.info("Pagamento PayPal para pedido {} aprovado. ID da transação: {}", payment.getOrderId(), payment.getTransactionId());
            // Chamar API do PayPal (ex: autorizar e capturar)
            // payPalClient.executePayment(payment.getAmount(), payment.getUserPayPalId());
        } else {
            payment.setPaymentStatus(PaymentStatus.DECLINED);
            payment.setUpdatedDate(LocalDateTime.now());
            logger.warn("Pagamento PayPal para pedido {} recusado. Motivo: Simulação de falha ou credenciais inválidas.", payment.getOrderId());
            // Lógica de tratamento de falha (ex: conta PayPal inválida)
        }
    }

    @Override
    public PaymentMethod getPaymentMethodType() {
        return PaymentMethod.PAYPAL;
    }
}