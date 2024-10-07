package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
