package com.rtarcisio.paymentms.domains;

import com.rtarcisio.paymentms.enums.PaymentMethod;
import com.rtarcisio.paymentms.enums.PaymentStatus;
import com.rtarcisio.paymentms.strategy.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "payment_id")
    private Long id;
    private String orderId;
    private Long userId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private LocalDateTime paymentDate;
    private LocalDateTime updatedDate;

    @Column(name = "transaction_id", unique=true)
    private String transactionId;

    public void processPayment(){
        PaymentStrategy strategy = getPaymentMethod().getStrategy();
        strategy.processPayment(this);
    }
}


