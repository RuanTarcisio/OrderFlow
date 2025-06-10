package com.rtarcisio.paymentms.strategy;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class PixPaymentStrategy implements PaymentStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PixPaymentStrategy.class);
    private final Random random = new Random();

    @Override
    public void processPayment(Payment payment) {
        logger.info("Iniciando processamento de pagamento PIX para o pedido: {}", payment.getOrderId());

        boolean success = random.nextDouble() < 0.80;

        if (success) {
            payment.setPaymentStatus(PaymentStatus.APPROVED);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setUpdatedDate(LocalDateTime.now());
            payment.setTransactionId(UUID.randomUUID().toString()); // Simula um ID de transação PIX
            logger.info("Pagamento PIX para pedido {} aprovado. ID da transação: {}", payment.getOrderId(), payment.getTransactionId());

            // Lógica para PIX (ex: gerar QR Code, verificar status da chave)
            // Chamar API externa do PIX para gerar QR Code ou verificar status
            // paymentGatewayClient.generatePixQrCode(payment.getAmount());
        } else {
            payment.setPaymentStatus(PaymentStatus.DECLINED);
            payment.setUpdatedDate(LocalDateTime.now());
            logger.warn("Pagamento PIX para pedido {} recusado. Motivo: Simulação de falha.", payment.getOrderId());
        }
    }

    @Override
    public PaymentMethod getPaymentMethodType() {
        return PaymentMethod.PIX;
    }
}
