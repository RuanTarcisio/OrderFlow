package com.rtarcisio.orderms.state;

import com.rtarcisio.orderms.domain.Order;

public interface OrderStateInterface {
    void next(Order order);
    void previous(Order order);
    void cancel(Order order);
}
