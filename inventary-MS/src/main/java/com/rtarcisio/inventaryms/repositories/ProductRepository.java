package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findAllByCategory(CategoryEnum category);

    List<Product> findAllByNameIsContainingIgnoreCase(String category);
}
