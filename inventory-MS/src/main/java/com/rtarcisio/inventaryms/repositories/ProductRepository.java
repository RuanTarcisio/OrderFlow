package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.dtos.projection.ProductProjection;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductGroup, String> {

    Optional<ProductGroup> findByName(String name);

    List<ProductGroup> findAllByCategory(CategoryEnum category);

    List<ProductGroup> findAllByNameIsContainingIgnoreCase(String category);

//    @Query("SELECT p.id AS id, p.name AS name, p.price AS price, p.category AS category, p.description AS description, i.id AS main_Image_Id FROM ProductGroup p LEFT JOIN ImageProduct i ON p.mainImage_id = i.id")
//    List<ProductProjection> findAllProjectedProducts();
}
