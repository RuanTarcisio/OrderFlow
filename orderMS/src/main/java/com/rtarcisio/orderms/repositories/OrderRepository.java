package com.rtarcisio.orderms.repositories;

import com.rtarcisio.orderms.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
