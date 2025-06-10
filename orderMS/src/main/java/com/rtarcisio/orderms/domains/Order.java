package com.rtarcisio.orderms.domains;

import com.rtarcisio.orderms.enums.PaymentMethod;
import com.rtarcisio.orderms.enums.PaymentStatus;
import com.rtarcisio.orderms.state.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long userId;

    @Column(name = "products_order")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> products = new ArrayList<>();

    private BigDecimal totalAmount;

    @Enumerated (EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    public void nextState() {
        state.next(this);
    }

    public void previousState() {
        state.previous(this);
    }

    public void cancelOrder() {
        state.cancel(this);
    }

    public void returnOrder() {
        if(this.state != OrderState.DELIVERED){
            throw new UnsupportedOperationException("Only delivered orders can be returned.");
        }
        state.next(this);
    }

    public void changePaymentMethod(String newPaymentMethod) {
        if (!state.equals(OrderState.NEW) && !state.equals(OrderState.PENDING_PAYMENT)) {
            throw new UnsupportedOperationException("Cannot change payment method after payment is completed or in process.");
        }
    }

    public String getStatus() {
        return state.name();
    }
}