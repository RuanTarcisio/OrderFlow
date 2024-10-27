package com.rtarcisio.orderms.state;

import com.rtarcisio.orderms.domains.Order;

public interface OrderStateInterface {
    void next(Order order);
    void previous(Order order);
    void cancel(Order order);
}
