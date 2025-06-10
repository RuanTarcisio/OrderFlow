package com.rtarcisio.paymentms.strategy;

import com.rtarcisio.paymentms.domains.Payment;
import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class BoletoPaymentStrategy implements PaymentStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BoletoPaymentStrategy.class);
    private final Random random = new Random();

    @Override
    public void processPayment(Payment payment) {
        logger.info("Iniciando processamento de pagamento por Boleto para o pedido: {}", payment.getOrderId());

        // A aprovação real viria de um webhook/callback bancário dias depois.
        boolean success = random.nextDouble() < 0.95; // Quase sempre gera o boleto

        if (success) {
            payment.setPaymentStatus(PaymentStatus.PENDING);
            payment.setUpdatedDate(LocalDateTime.now());
            payment.setTransactionId(UUID.randomUUID().toString()); // Simula um ID do boleto gerado
            logger.info("Boleto para pedido {} gerado com sucesso. ID do Boleto: {}. Status: PENDENTE.", payment.getOrderId(), payment.getTransactionId());
            // Chamar API externa para gerar o boleto e obter o código de barras
            // boletoService.generateBoleto(payment.getAmount(), payment.getUserId(), payment.getOrderId());
        } else {
            payment.setPaymentStatus(PaymentStatus.DECLINED); // Ou FAILED_TO_GENERATE
            payment.setUpdatedDate(LocalDateTime.now());
            logger.warn("Falha ao gerar Boleto para pedido {}. Motivo: Simulação de falha.", payment.getOrderId());
            // Lógica de tratamento de falha na geração do boleto
        }
    }

    @Override
    public PaymentMethod getPaymentMethodType() {
        return PaymentMethod.BOLETO;
    }
}