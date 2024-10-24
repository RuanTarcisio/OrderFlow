package com.rtarcisio.orderms.domain;

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

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private Long user_id;

    @ElementCollection
    private List<String> products_id;

    private BigDecimal totalAmount;

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

    public String getStatus() {
        return state.name();
    }
}