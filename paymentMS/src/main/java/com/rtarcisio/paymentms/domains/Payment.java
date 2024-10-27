package com.rtarcisio.paymentms.domains;

import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único do pagamento
    private String orderId; // ID do pedido associado
    private Long userId; // ID do usuário que realizou o pagamento
    private BigDecimal amount; // Valor do pagamento
    private PaymentMethod paymentMethod; // Enum de métodos de pagamento (CARTÃO, BOLETO, PIX, etc.)
    private PaymentStatus paymentStatus; // Enum para o status do pagamento (PENDENTE, CONCLUÍDO, CANCELADO)
    private LocalDateTime paymentDate; // Data do pagamento
    private LocalDateTime updatedDate; // Data da última atualização do pagamento
    private String transactionId; // ID da tra

    private void processPayment(){
        this.paymentMethod.getStrategy().processPayment(this);
    }
}
