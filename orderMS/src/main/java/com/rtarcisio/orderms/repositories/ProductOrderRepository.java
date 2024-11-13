package com.rtarcisio.orderms.repositories;

import com.rtarcisio.orderms.domains.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {
}
