package com.rtarcisio.orderms.domains;

import com.rtarcisio.orderms.state.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long userId;

    @ElementCollection
    private List<String> productsId;

    private BigDecimal totalAmount;

    private Boolean paymentApproved;

    private String paymentId;

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

        // Lógica para chamar o Payment MS e alterar a forma de pagamento
//        PaymentService.changePaymentMethod(paymentId, newPaymentMethod);

        // Atualize o pedido para refletir a nova forma de pagamento (caso necessário)
        this.paymentId = newPaymentMethod;  // ou um novo paymentId
    }

    public String getStatus() {
        return state.name();
    }
}