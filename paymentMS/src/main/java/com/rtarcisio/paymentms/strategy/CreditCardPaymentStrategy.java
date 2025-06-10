package com.rtarcisio.paymentms.strategy;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CreditCardPaymentStrategy.class);
    private final Random random = new Random();

    @Override
    public void processPayment(Payment payment) {
        logger.info("Iniciando processamento de pagamento com Cartão de Crédito para o pedido: {}", payment.getOrderId());

        // Simulação: 90% de chance de sucesso para Cartão de Crédito
        boolean success = random.nextDouble() < 0.90;

        if (success) {
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setUpdatedDate(LocalDateTime.now());
            payment.setTransactionId(UUID.randomUUID().toString()); // Simula ID da transação do cartão
            logger.info("Pagamento com Cartão de Crédito para pedido {} aprovado. ID da transação: {}", payment.getOrderId(), payment.getTransactionId());
            // Chamar API externa da adquirente/gateway de pagamento (ex: Stripe, Cielo)
            // creditCardGatewayClient.authorizeAndCapture(payment.getAmount(), payment.getCardDetails());
        } else {
            payment.setPaymentStatus(PaymentStatus.DECLINED);
            payment.setUpdatedDate(LocalDateTime.now());
            logger.warn("Pagamento com Cartão de Crédito para pedido {} recusado. Motivo: Simulação de falha ou cartão inválido.", payment.getOrderId());
            // Lógica de tratamento de falha (ex: cartão recusado, limite excedido)
        }
    }

    @Override
    public PaymentMethod getPaymentMethodType() {
        return PaymentMethod.CREDIT_CARD;
    }
}