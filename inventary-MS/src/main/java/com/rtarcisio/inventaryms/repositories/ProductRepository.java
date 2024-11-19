package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.projection.ProductProjection;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findAllByCategory(CategoryEnum category);

    List<Product> findAllByNameIsContainingIgnoreCase(String category);

    @Query("SELECT p.id AS id, p.name AS name, p.price AS price, p.category AS category, p.description AS description, i.id AS main_Image_Id FROM Product p LEFT JOIN ImageProduct i ON p.mainImage_id = i.id")
    List<ProductProjection> findAllProjectedProducts();
}
