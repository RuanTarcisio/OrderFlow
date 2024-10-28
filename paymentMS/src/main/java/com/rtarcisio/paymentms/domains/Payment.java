package com.rtarcisio.paymentms.domains;

import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import com.rtarcisio.paymentms.strategy.PaymentStrategy;
import jakarta.persistence.*;
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
    private Long id;
    private String orderId;
    private Long userId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private LocalDateTime updatedDate;

    @Column(name = "transaction_id", unique=true)
    private String transactionId;

    public void processPayment(){
        PaymentStrategy strategy = getPaymentMethod().getStrategy();
        strategy.processPayment(this);

        /*
        PIX TENTA PAGAR 2x ganha desconto... fazeer para os outros

         */
    }
}
